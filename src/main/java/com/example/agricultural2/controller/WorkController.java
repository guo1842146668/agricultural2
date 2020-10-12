package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.entity.User;
import com.example.agricultural2.entity.Work;
import com.example.agricultural2.service.WorkService;
import com.example.agricultural2.tool.ExprotExcel;
import com.example.agricultural2.tool.JavaWebToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: WorkController
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 14:36
 * @Version: 1.0
 **/
@Api(value = "WorkController", tags = "作业信息控制类")
@CrossOrigin
@RestController
@RequestMapping("/work")
public class WorkController {
    @Resource
    private WorkService workService;

    /**
     * @Author guoyangyang
     * @Description  作业信息表插入
     * @Date  2020/7/24 8:54
     * @Param * @param work:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="录入作业信息", notes="录入作业信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work", value = "作业信息实体类", required = true ,dataType = "com.puyan.shengren.agricultural.enity.Work"),
    })
    @PostMapping("/add")
    @ResponseBody
    public Result insert(Work work){
        return  ResultUtil.seccess(workService.insert(work));
    }

    /**
     * @Author guoyangyang
     * @Description  作业信息表根据ID删除
     * @Date  2020/7/24 8:54
     * @Param * @param workID:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="根据ID删除作业信息", notes="根据ID删除作业信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workID", value = "作业信息ID", required = true ,dataType = "Integer"),
    })
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delete(Integer workID){
         return ResultUtil.seccess(workService.delete(workID));
    }

    /**
     * @Author guoyangyang
     * @Description  作业信息表根据ID修改信息
     * @Date  2020/7/24 8:54
     * @Param * @param work:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="修改作业信息", notes="修改作业信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work", value = "作业信息实体类", required = true ,dataType = "com.puyan.shengren.agricultural.enity.Work"),
    })
    @PutMapping("/update")
    @ResponseBody
    public Result update(Work work){
        return  ResultUtil.seccess(workService.update(work));
    }

    /**
     * @Author guoyangyang
     * @Description  获取作业信息表所有的数据
     * @Date  2020/7/24 8:57
     * @Param * @param map:
     * @param page:
     * @param count:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="查询所有作业信息", notes="查询所有作业信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work", value = "筛选条件", required = false ,dataType = "com.puyan.shengren.agricultural.enity.Work"),
            @ApiImplicitParam(name = "page", value = "分页 - 第几页", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "count", value = "分页 - 一页几条数据", required = true ,dataType = "Integer",paramType = "query")
    })
    @GetMapping("/getAll")
    @ResponseBody
    public Result getAll(@RequestParam(value = "page",defaultValue = "1") Integer page,
                         @RequestParam(value = "count",defaultValue = "10") Integer count,
                         HttpServletRequest request,Work work){
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        if(user.getType() == 1) {
            return ResultUtil.seccess(workService.getAllAdmin(work,page, count));
        }else{
            return ResultUtil.seccess(workService.getAllUser(work,user.getDeptId(),page, count));
        }
    }


    @ApiOperation(value = "上传图片文件,支持多图片上传")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true,dataType = "MultipartFile",allowMultiple = true),
            @ApiImplicitParam(name = "workID", value = "需要归属于那一条记录的ID", required = true, dataType = "Integer", paramType = "query")
    })

  /*  @PostMapping(value="/uploadImages")
    @ResponseBody
    public Result uploadImages(@RequestParam(value="file") MultipartFile[] files, Integer workID){
      return  ResultUtil.seccess(workService.uploadImgs(files, workID));
    }*/

    @GetMapping("/uploadExcel")
    @ResponseBody
    public Result uploadExcel(HttpServletRequest request, HttpServletResponse response,String workID,String machineryOwner) throws IOException, ParseException {

        Map<String,Object> headerList=new HashMap<>();
        headerList.put("序号","1");
        headerList.put("用户名","username");
        headerList.put("作业开始时间","work_start_time");
        headerList.put("作业开始坐标","work_start_map");
        headerList.put("作业结束时间","work_end_time");
        headerList.put("作业结束坐标","work_end_map");
        headerList.put("行驶里程","driven_distance");
        headerList.put("作业里程","work_length");
        headerList.put("作业面积","work_area");
        headerList.put("作业深度","work_depth");
        headerList.put("核对结果","verification_results");
        headerList.put("确认面积","confirm_area");
        headerList.put("核对面积","confirm_area");
        headerList.put("省","province");
        headerList.put("市","city");
        headerList.put("县","county");
        headerList.put("镇","town");
        headerList.put("村","village");

        /*List<Map<String, Object>> maps;
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        if(user.getType() == 1) {
            maps = workService.upExcelAdmin();
        }else{
            maps = workService.upExcelUser(user.getDeptId());
        }*/
        List<Map<String, Object>> maps = workService.upExcelUser(workID,machineryOwner);
        ExprotExcel.exportAll(maps,headerList,request,response);
        return ResultUtil.seccess();
    }


}
