package com.example.agricultural2.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.Machinery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MachineryService
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 13:39
 * @Version: 1.0
 **/
public interface MachineryService extends IService<Machinery> {

    int insert(Machinery machinery);

    int updateStatus(Integer machineryID,Integer machineryStatus);

    int update(Machinery machinery);

    PageInfo<Map<String,Object>> getListAdmin(Integer page, Integer count);

    Map<String,Object> getMachineryIsEmpty(String machineryNo);

    int deleteMachinery(Integer machineryID);

    PageInfo<Map<String,Object>> getListUser(Integer deptId,Integer page, Integer count);

    PageInfo<Map<String,Object>> getVerificationAdmin(Integer page, Integer count);

    PageInfo<Map<String,Object>> getVerificationUser(Integer page, Integer count,Integer deptId);
}
