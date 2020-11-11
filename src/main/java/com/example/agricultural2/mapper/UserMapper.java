package com.example.agricultural2.mapper;

import com.example.agricultural2.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getListUser(Integer deptId);

    String getDeptName(Integer deptId);
}
