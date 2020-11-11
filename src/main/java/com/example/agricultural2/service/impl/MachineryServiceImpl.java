package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.Machinery;
import com.example.agricultural2.mapper.MachineryDao;
import com.example.agricultural2.service.MachineryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MachineryServiceImpl
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 13:39
 * @Version: 1.0
 **/
@Service
public class MachineryServiceImpl extends ServiceImpl<MachineryDao, Machinery> implements MachineryService {
    @Resource
    private MachineryDao machineryDao;

    @Override
    public int insert(Machinery machinery) {
        machinery.setMachineryStatus(1);
        return machineryDao.insert(machinery);
    }

    @Override
    public int updateStatus(Integer machineryID, Integer machineryStatus) {
        Machinery machinery = new Machinery();
        machinery.setMachineryId(machineryID);
        machinery.setMachineryStatus(machineryStatus);
        return machineryDao.insert(machinery);
    }

    @Override
    public int update(Machinery machinery) {
        return  machineryDao.updateById(machinery);
    }

    @Override
    public PageInfo<Map<String,Object>> getListAdmin(Integer page, Integer count,Integer dictId) {
        PageHelper.startPage(page,count);
        List<Map<String,Object>> machineryList = machineryDao.getListAdmin(dictId);
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<>(machineryList);
        return pageInfo;
    }

    @Override
    public Map<String,Object> getMachineryIsEmpty(String machineryNo) {
        return machineryDao.getMachineryIsEmpty(machineryNo);
    }

    @Override
    public int deleteMachinery(Integer machineryID) {
        machineryDao.deleteMachinery(machineryID);
        machineryDao.deleteWork(machineryID);
        return 1;
    }

    @Override
    public PageInfo<Map<String, Object>> getListUser(Integer deptId, Integer page, Integer count,Integer dictId) {
        PageHelper.startPage(page,count);
        List<Map<String,Object>> machineryList = machineryDao.getListUser(deptId,dictId);
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<>(machineryList);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getVerificationAdmin(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Map<String,Object>> machineryList = machineryDao.getVerificationAdmin();
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<>(machineryList);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getVerificationUser(Integer page, Integer count,Integer deptId) {
        PageHelper.startPage(page,count);
        List<Map<String,Object>> machineryList = machineryDao.getVerificationUser(deptId);
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<>(machineryList);
        return pageInfo;
    }
}
