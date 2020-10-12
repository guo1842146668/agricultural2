package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.entity.Machinery;
import com.example.agricultural2.entity.User;
import com.example.agricultural2.service.MachineryService;
import com.example.agricultural2.tool.JavaWebToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: MachineryController
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 13:53
 * @Version: 1.0
 **/
@Api(value = "/machinery", description = "农业机械操作类")
@CrossOrigin
@RestController
@RequestMapping("/machinery")
public class MachineryController {
    @Resource
    private MachineryService machineryService;

    /**
     * @Author guoyangyang
     * @Description  录入农业机械
     * @Date  2020/7/23 18:42
     * @Param * @param machinery:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="添加农业机械", notes="添加农业机械接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "machinery", value = "机械实体类", required = true ,dataType = "com.puyan.shengren.agricultural.enity.Machinery",paramType = "query"),
    })
    @PostMapping("/add")
    public Result add(Machinery machinery){
        return ResultUtil.seccess(machineryService.insert(machinery));
    }

    /**
     * @Author guoyangyang
     * @Description  修改农业机械的状态
     * @Date  2020/7/23 18:42
     * @Param * @param machineryID:
     * @param machineryStatus:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="更改农业机械状态", notes="更改农业机械状态接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "machineryID", value = "农业机械ID", required = true ,dataType = "Integer"),
            @ApiImplicitParam(name = "machineryStatus", value = "农业机械状态", required = true ,dataType = "Integer"),
    })
    @PutMapping("/updateStatus")
    public Result updateStatus(Integer machineryID,Integer machineryStatus){
        return ResultUtil.seccess(machineryService.updateStatus(machineryID, machineryStatus));
    }

    /**
     * @Author guoyangyang
     * @Description  根据ID修改农业机械的信息
     * @Date  2020/7/23 18:43
     * @Param * @param machinery:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="修改农业机械信息", notes="修改农业机械信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "machinery", value = "机械实体类", required = true ,dataType = "com.puyan.shengren.agricultural.enity.Machinery"),
    })
    @PutMapping("/update")
    @ResponseBody
    public Result update(Machinery machinery){
        return ResultUtil.seccess(machineryService.update(machinery));
    }

    /**
     * @Author guoyangyang
     * @Description  查询所有的农业机械
     * @Date  2020/7/23 18:43
     * @Param * @param map:
     * @param page:
     * @param count:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="获取所有农业机械信息", notes="获取所有农业机械信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Machinery", value = "筛选条件", required = false ,dataType = "com.puyan.shengren.agricultural.enity.Machinery"),
            @ApiImplicitParam(name = "page", value = "分页 - 第几页", required = true ,dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "count",value = "分页 - 一页几条数据", required = true ,dataType = "Integer",paramType="query")
    })
    @GetMapping("/getAll")
    @ResponseBody
    public Result getAll(@RequestParam(value = "page",defaultValue = "1") Integer page,
                         @RequestParam(value = "count",defaultValue = "10") Integer count,
                         HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        if(user.getType() == 1){
            return ResultUtil.seccess(machineryService.getListAdmin(page, count));
        }else{
            return ResultUtil.seccess(machineryService.getListUser(user.getDeptId(),page,count));
        }

    }

    /**
     * 通过机械No查询此编号是否存在
     * @param machineryNo 机械编号
     * @return
     */
    @GetMapping("/getMachineryIsEmpty")
    @ResponseBody
    public Result getMachineryIsEmpty(String machineryNo){
        return ResultUtil.seccess(machineryService.getMachineryIsEmpty(machineryNo));
    }


    @DeleteMapping("/delete")
    public Result deleteWork(Integer machineryID){
        return ResultUtil.seccess(machineryService.deleteMachinery(machineryID));
    }

    /**
     * @Author guoyangyang
     * @Description  查询所有的农业机械
     * @Date  2020/7/23 18:43
     * @Param * @param map:
     * @param page:
     * @param count:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="获取所有农业机械信息", notes="获取所有农业机械信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Machinery", value = "筛选条件", required = false ,dataType = "com.puyan.shengren.agricultural.enity.Machinery"),
            @ApiImplicitParam(name = "page", value = "分页 - 第几页", required = true ,dataType = "Integer",paramType="query"),
            @ApiImplicitParam(name = "count",value = "分页 - 一页几条数据", required = true ,dataType = "Integer",paramType="query")
    })
    @GetMapping("/getVerification")
    @ResponseBody
    public Result getVerification(@RequestParam(value = "page",defaultValue = "1") Integer page,
                         @RequestParam(value = "count",defaultValue = "10") Integer count,
                         HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        if(user.getType() == 1){
            return ResultUtil.seccess(machineryService.getVerificationAdmin(page, count));
        }else{
            return ResultUtil.seccess(machineryService.getVerificationUser(user.getDeptId(),page,count));
        }

    }
}
