package com.example.agricultural2.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.agricultural2.mapper.StatDao;
import com.example.agricultural2.service.IStatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StatServiceImpl
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 15:46
 * @Version: 1.0
 **/
@Service
public class StatServiceImpl implements IStatService {
    @Resource
    private StatDao statDao;


    @Override
    public PageInfo<Map<String,Object>> getAllByCounty(Integer page, Integer count, Integer checkID, String workStartTime,String workEndTime,Integer deptId,Integer dictId) {
        PageHelper.startPage(page,count);
        HashMap hashMap=new HashMap();
        hashMap.put("checkID",checkID);
        hashMap.put("workStartTime",workStartTime);
        hashMap.put("workEndTime",workEndTime);
        hashMap.put("deptId",deptId);
        hashMap.put("dictId",dictId);
        List<Map<String, Object>> allByCounty = statDao.getAllByCounty(hashMap);
        if(allByCounty.isEmpty()){
            return null;
        }
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(allByCounty);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String,Object>> getAllByMachineryID(Integer page,Integer count,Integer deptId) {
        PageHelper.startPage(page,count);
        List<Map<String, Object>> allByMachineryID = statDao.getAllByMachineryID(deptId);
        if(allByMachineryID.isEmpty()){
            return null;
        }
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(allByMachineryID);
        return pageInfo;
    }

    @Override
    public List<Map<String,Object>> getStatsWookNum(Integer deptId,Integer dictId) {
        return statDao.getStatsWookNum(deptId,dictId);
    }

    @Override
    public List<Map<String,Object>> getPastSeven(Integer deptId,Integer dictId) {
        return statDao.getPastSeven(deptId,dictId);
    }

    @Override
    public List<Map<String,Object>> getDecember(String time,Integer deptId,Integer dictId) {
        if(StrUtil.hasBlank(time)  || time.equals("")){
            return statDao.getDecember(null,deptId,dictId);
        }
        return statDao.getDecember(time,deptId,dictId);
    }

    @Override
    public List<Map<String,Object>> getWorkBycounty(String county,Integer deptId,Integer dictId) {
        return statDao.getWorkBycounty(county,deptId,dictId);
    }

    @Override
    public List<Map<String,Object>> getWorkByNo(String machineryNO) {
        return statDao.getWorkByNo(machineryNO);
    }

    @Override
    public List<Map<String,Object>> getWorkByvillage(Integer  workID) {
        return statDao.getWorkByvillage(workID);
    }

    @Override
    public List<Map<String,Object>> getWorkByName(Integer  userID) {
        return statDao.getWorkByName(userID);
    }

    @Override
    public List<Map<String,Object>> getAllByCounty(Integer deptId,Integer dictId) {
        return statDao.getByCounty(deptId,dictId);
    }

    @Override
    public List<Map<String, Object>> longitudeAndLatitude(Integer deptId, Integer dictId) {
        return statDao.longitudeAndLatitude(deptId,dictId);
    }

    @Override
    public List<Map<String, Object>> everyday(Integer deptId,Integer dictId,Integer year,Integer month) {
        return statDao.everyday(deptId, dictId,year,month);
    }
}
