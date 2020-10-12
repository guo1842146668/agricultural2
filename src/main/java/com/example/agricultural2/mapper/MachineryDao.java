package com.example.agricultural2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.agricultural2.entity.Machinery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: MachineryDao
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 11:05
 * @Version: 1.0
 **/
@Mapper
public interface MachineryDao extends BaseMapper<Machinery> {

    int updateStatus(Integer machineryID,Integer machineryStatus);

    List<Map<String,Object>> getListAdmin();

    List<Map<String,Object>> getListUser(Integer deptId);

    Map<String,Object> getMachineryIsEmpty(String machineryNO);

    int deleteMachinery(Integer machineryID);

    int deleteWork(Integer machineryID);

    Map<String,Object> getMachineryByID(Integer  machineryID);

    List<Map<String,Object>> getVerificationAdmin();

    List<Map<String,Object>> getVerificationUser(Integer deptId);
}
