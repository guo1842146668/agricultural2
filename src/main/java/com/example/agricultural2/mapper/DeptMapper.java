package com.example.agricultural2.mapper;

import com.example.agricultural2.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> getListUser(Integer deptId);

    List<Map<String,Object>> getDeptMenu(Integer deptId);

    int deleteByDeptId(Integer deptId);

    int insertDeptMenu(Integer deptId,Integer menuId);
}
