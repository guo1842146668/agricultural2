package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.agricultural2.entity.User;
import com.example.agricultural2.mapper.UserMapper;
import com.example.agricultural2.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.tool.ClassIsNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public int checkUsername(String username) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return  userMapper.selectCount(queryWrapper);
    }

    @Override
    public List<User> getListAdmin(String username, String phone, String status) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(username != null){
            queryWrapper.eq("username",username);
        }
        if(phone !=null){
            queryWrapper.eq("phone",phone);
        }
        if(status != null){
            queryWrapper.eq("status",status);
        }
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> getListUser(String username, String phone, String status, Integer deptId) {
        return userMapper.getListUser(deptId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int delete(Integer userID) {
        return userMapper.deleteById(userID);
    }

    @Override
    public String getDeptName(Integer deptId) {
        return userMapper.getDeptName(deptId);
    }
}
