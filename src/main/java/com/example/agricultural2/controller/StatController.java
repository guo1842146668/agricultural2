package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.service.IStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @ClassName: StatController
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 15:53
 * @Version: 1.0
 **/
@Api(value = "StatController", description = "统计信息控制类")
@CrossOrigin
@RestController
@RequestMapping("/stat")
public class StatController {
    @Resource
    private IStatService statService;

    /**
     * @Author guoyangyang
     * @Description  根据县/区统计 农机数量-作业里程-作业面积
     * @Date  2020/7/23 16:05
     * @Param * @param page:
     * @param count:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="县/区统计", notes="县/区统计接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页 - 第几页", required = true ,dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "count", value = "分页 - 一页几条数据", required = true ,dataType = "Integer", paramType = "query")
    })
    @GetMapping("/getAllByCounty")
    @ResponseBody
    public Result getAllByCounty(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                 @RequestParam(value = "count",defaultValue = "10") Integer count,
                                 Integer checkID, String workStartTime, String workEndTime, Integer deptId){
        return ResultUtil.seccess(statService.getAllByCounty(page, count,checkID,workStartTime,workEndTime,deptId));
    }

    /**
     * @Author guoyangyang
     * @Description  根据农机类型统计 农机数量-作业里程-作业面积
     * @Date  2020/7/23 16:06
     * @Param * @param page:
 * @param count:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="农机类型统计", notes="农机类型统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页 - 第几页", required = true ,dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "count", value = "分页 - 一页几条数据", required = true ,dataType = "Integer", paramType = "query")
    })
    @GetMapping("/getAllByMachineryID")
    @ResponseBody
    public Result getAllByMachineryID(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "count",defaultValue = "10") Integer count,
                                      Integer deptId){
        return ResultUtil.seccess(statService.getAllByMachineryID(page, count,deptId));
    }

    /**
     * @Author guoyangyang
     * @Description  统计所有/昨天/今天/ 的农机数，与作业面积
     * @Date  2020/7/24 11:22
     * @Param
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="统计所有/昨天/今天/ 的农机数，与作业面积", notes="统计接口")
    @GetMapping("/getStatsWookNum")
    @ResponseBody
    public Result getStatsWookNum(Integer deptId) {
        return ResultUtil.seccess(statService.getStatsWookNum(deptId));
    }

    /**
     * @Author guoyangyang
     * @Description  统计过去7天的作业面积
     * @Date  2020/7/24 11:24
     * @Param 
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="统计过去7天的作业面积", notes="统计过去7天的作业面积接口")
    @GetMapping("/getPastSeven")
    @ResponseBody
    public Result getPastSeven(Integer deptId) {
        return ResultUtil.seccess(statService.getPastSeven(deptId));
    }


    /**
     * @Author guoyangyang
     * @Description  统计过去12个月的作业面积
     * @Date  2020/7/24 11:24
     * @Param
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="统计过去12个月的作业面积", notes="统计过去12个月的作业面积接口")
    @GetMapping("/getDecember")
    @ResponseBody
    public Result getDecember(String time,Integer deptId) {
        return ResultUtil.seccess(statService.getDecember(time,deptId));
    }

    @GetMapping("/getWorkBycounty")
    public Result getWorkBycounty(String county,Integer deptId){
        return ResultUtil.seccess(statService.getWorkBycounty(county,deptId));
    }

    @GetMapping("/getWorkByNo")
    public Result getWorkByNo(String machineryNO){
        return ResultUtil.seccess(statService.getWorkByNo(machineryNO));
    }

    @GetMapping("/getWorkByvillage")
    public Result getWorkByvillage(Integer  workID){
        return ResultUtil.seccess(statService.getWorkByvillage(workID));
    }

    @GetMapping("/getWorkByName")
    public Result getWorkByName(Integer  userID){
        return ResultUtil.seccess(statService.getWorkByName(userID));
    }

    @ApiOperation(value="县/区统计", notes="县/区统计接口")
    @GetMapping("/getByCounty")
    @ResponseBody
    public Result getByCounty(Integer deptId){
        return ResultUtil.seccess(statService.getAllByCounty(deptId));
    }
}
