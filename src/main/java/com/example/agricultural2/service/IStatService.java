package com.example.agricultural2.service;


import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StatService
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 15:46
 * @Version: 1.0
 **/
public interface IStatService {

    PageInfo<Map<String,Object>> getAllByCounty(Integer page, Integer count, Integer checkID, String workStartTime, String workEndTime, Integer deptId);

    PageInfo<Map<String,Object>> getAllByMachineryID(Integer page,Integer count,Integer deptId);

    List<Map<String,Object>> getStatsWookNum(Integer deptId);

    List<Map<String,Object>> getPastSeven(Integer deptId);

    List<Map<String,Object>> getDecember(String time,Integer deptId);

    List<Map<String,Object>> getWorkBycounty(String  county,Integer deptId);

    List<Map<String,Object>> getWorkByNo(String  machineryNO);

    List<Map<String,Object>> getWorkByvillage(Integer  workID);

    List<Map<String,Object>> getWorkByName(Integer  userID);

    List<Map<String,Object>> getAllByCounty(Integer deptId);
}
