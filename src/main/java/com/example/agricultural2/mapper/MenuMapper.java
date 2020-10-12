package com.example.agricultural2.mapper;

import com.example.agricultural2.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getListDept(Integer deptId);
}
