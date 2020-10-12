package com.example.agricultural2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.agricultural2.entity.Work;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkDao
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 14:03
 * @Version: 1.0
 **/
@Mapper
public interface WorkDao extends BaseMapper<Work> {

    List<Map<String,Object>> getAllAdmin(Work work);

    List<Map<String,Object>> getAllUser(Work work,Integer deptId);

    List<Map<String,Object>> upExcelAdmin();

    List<Map<String,Object>> upExcelUser(String workID,String machineryOwner);

    Work getByWorkID(Integer workID);
}
