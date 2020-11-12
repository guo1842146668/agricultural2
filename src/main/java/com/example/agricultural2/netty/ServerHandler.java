package com.example.agricultural2.netty;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.example.agricultural2.entity.Machinery;
import com.example.agricultural2.entity.Work;
import com.example.agricultural2.service.WorkService;
import com.example.agricultural2.service.impl.MachineryServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JHS
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    //存放Socket连接
    public static TimedCache<String, ChannelHandlerContext> timedCache = CacheUtil.newTimedCache(10000);

    //存放设备与服务器的连接
    public static TimedCache<String, NettyServer> NETTY_SERVER = CacheUtil.newTimedCache(5000);

    //链接
    public static ChannelHandlerContext chc;

    private static Map<String,Integer> IO_OPEN = new HashMap<>();

    private static Map<String,Object> IMEI = new HashMap<>();

    private WorkService workService;

    private MachineryServiceImpl machineryService;

    public ServerHandler(MachineryServiceImpl machineryService,WorkService workService){
            this.machineryService = machineryService;
            this.workService = workService;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        ServerHandler.chc = ctx;
        // 收到消息直接打印输出
        System.err.println(DateUtil.now() + ":" + ctx.channel().remoteAddress().toString().split(":")[0] + " Say : " + msg);
        //跟踪器ID
        String trackerID;
        //命令
        String command;
        // 返回客户端消息 - 我已经接收到了你的消息

        if(msg.length() > 16){
            trackerID = msg.substring(1,13);
            command = msg.substring(13,17);
            if("BP05".equals(command)){
                //回复注册设备
                ctx.writeAndFlush("("+trackerID+"AP05)");
                IMEI.put(trackerID,msg.substring(17,32));
                //BR00 发送实时位置信息
            }else if("BR00".equals(command) || "BR01".equals(command)){
                String  IOStatus = msg.substring(62,67);
                //开始工作
                if("01100".equals(IOStatus)){
                    Map<String, Object> map = terminalRegistration(msg);
                    Map<String, Object> machineryIsEmpty = machineryService.getMachineryIsEmpty(trackerID);
                    //创建一条工作信息
                    if(IO_OPEN == null || IO_OPEN.get(trackerID) == null){
                        //判断是否存在这个设备
                        if(machineryIsEmpty == null || machineryIsEmpty.get("machinery_id") == null || "".equals(machineryIsEmpty.get("machinery_id"))){
                            //不存在添加一个设备信息
                            Machinery machinery = new Machinery();
                            machinery.setMachineryNo(trackerID);
                            machinery.setMachineryImel(IMEI.get(trackerID).toString());
                            machinery.setMachineryStatus(1);
                            machineryService.save(machinery);
                            Work work = new Work();
                            work.setWorkMachineryId(machinery.getMachineryId());
                            work.setWorkStartTime(new Date());
                            work.setWorkStartMap(map.get("work_map").toString());
                            work.setDrivenDistance(Double.valueOf(map.get("mileage").toString()));
                            work.setWorkArea(0.00);
                            work.setWorkDepth(0.00);
                            workService.save(work);
                            IO_OPEN.put(trackerID,work.getWorkId());
                        }else{
                            //存在添加工作信息
                            Work work = new Work();
                            work.setWorkMachineryId(Integer.parseInt(machineryIsEmpty.get("machinery_id").toString()));
                            work.setWorkStartTime(new Date());
                            work.setWorkStartMap(map.get("work_map").toString());
                            work.setDrivenDistance(Double.valueOf(map.get("mileage").toString()));
                            work.setWorkArea(0.00);
                            work.setWorkDepth(0.00);
                            work.setWorkLength(0.00);
                            workService.save(work);
                            IO_OPEN.put(trackerID,work.getWorkId());
                        }
                    }else{
                        //累加里程信息
                        Integer workID = IO_OPEN.get(trackerID);
                        Work workById = workService.getById(workID);
                        workById.setWorkLength(workById.getWorkLength()+(Double.parseDouble(map.get("mileage").toString())-workById.getDrivenDistance()));
                        workById.setDrivenDistance(Double.valueOf(map.get("mileage").toString()));
                        workById.setWorkArea(workById.getWorkLength()*Double.parseDouble(machineryIsEmpty.get("machinery_width").toString()));
                        workService.update(workById);
                    }
                }else if("00100".equals(IOStatus)){
                    //结束工作
                    if(IO_OPEN.get(trackerID) != null){
                        Map<String, Object> map = terminalRegistration(msg);
                        Map<String, Object> machineryIsEmpty = machineryService.getMachineryIsEmpty(trackerID);
                        Integer workID = IO_OPEN.get(trackerID);
                        Work workById = workService.getById(workID);
                        workById.setWorkLength(workById.getWorkLength()+(Double.parseDouble(map.get("mileage").toString())-workById.getDrivenDistance()));
                        workById.setWorkArea(workById.getWorkLength()*Double.parseDouble(machineryIsEmpty.get("machinery_width").toString()));
                        workById.setWorkEndMap(map.get("work_map").toString());
                        workById.setWorkEndTime(new Date());
                        workService.update(workById);
                    }
                    IO_OPEN.remove(trackerID);
                }
            }
        }
        ctx.writeAndFlush("111111");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(":01031C1C005074\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /*
     *
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ServerHandler.timedCache.put(ctx.channel().remoteAddress().toString().split(":")[0], ctx);
        System.out.println("地址 : " + ctx.channel().remoteAddress().toString().split(":")[0]);
        //向客户端发送的信息
        //ctx.writeAndFlush("11 00 01 00");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    ctx.writeAndFlush(":01031C1C005074\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        ctx.writeAndFlush("22222");
        super.channelActive(ctx);
    }


    /**
     * 处理终端注册信息
     *
     * @param msg 命令
     */
    public static Map<String,Object> terminalRegistration(String msg) {
        Map<String,Object> map = new HashMap<>();
        //设备编号
        String equipmentNumber = msg.substring(1, 13);
        map.put("equipmentNumber",equipmentNumber);
        //IMEI  国际移动设备识别码
      /*  String imei = msg.substring(17, 32);
        map.put("imei",imei);*/
        //纬度
        String latitudeGPS = msg.substring(24, 33);
        double latitude =Double.parseDouble(latitudeGPS.substring(0,latitudeGPS.indexOf(".")-2))+Double.parseDouble(latitudeGPS.substring(latitudeGPS.indexOf(".")-2))/60;
        //经度
        String longitudeGPS = msg.substring(34, 44);
        double longitude =Double.parseDouble(longitudeGPS.substring(0,longitudeGPS.indexOf(".")-2))+Double.parseDouble(longitudeGPS.substring(longitudeGPS.indexOf(".")-2))/60;
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://restapi.amap.com/v3/assistant/coordinate/convert?locations=" + longitude + "," + latitude + "&coordsys=gps&output=xml&key=b1e7d4a485aa5e9af28f2ba8a2a28eee", String.class);
        JSONObject jsonObject = XML.toJSONObject(forObject);
        Object response = jsonObject.get("response");
        JSON parse = JSONUtil.parse(response);
        map.put("work_map",parse.getByPath("locations"));
        //里程数
        Integer mileage = Integer.parseInt(msg.substring(msg.indexOf("L")+1,msg.indexOf("L")+9),16);
        map.put("mileage",mileage);
        return map;
    }

    /**
     * 设置 ACC 开终端设备发送间隔
     *
     * @param deviceId     设备ID
     * @param timeInterval 时间间隔
     *                     new BigInteger(timeInterval + "", 10).toString(16)  10进制转16进制
     */
    public static void setTheAccOnTerminalDeviceSendingInterval(String deviceId, Integer timeInterval) {
        String order = "(" + deviceId + "AR05" + zeroPadding(new BigInteger(timeInterval + "", 10).toString(16)) + ")";
    }

    /**
     * 设置 ACC 关终端设备发送间隔
     *
     * @param deviceId
     * @param timeInterval
     */
    public static void setTheAccTerminalEquipmentSendingInterval(String deviceId, Integer timeInterval) {
        String order = "(" + deviceId + "AR06" + zeroPadding(new BigInteger(timeInterval + "", 10).toString(16)) + ")";
    }

    public static void main(String[] args) {
        terminalRegistration("(283036303034373239353236304250303538363336373430343232303236363130303030303056303030303030303030533030303030303030303057303030303030383430353430303030303030313130303030304C4646464646464646503037415332333029)");
        setTheAccOnTerminalDeviceSendingInterval("060047295260", 291);
    }


    /**
     * 补零
     *
     * @return 补0结果
     */
    public static String zeroPadding(String msg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            sb.append("0");
        }
        sb.append(msg);
        return msg;
    }
}
