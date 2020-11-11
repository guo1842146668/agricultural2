package com.example.agricultural2.service;

import com.example.agricultural2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
public interface IUserService extends IService<User> {
    /**
     * 添加用户
     * @param user 用户实体
     * @return 添加结果
     */
    int saveUser(User user);

    /**
     * 登陆
     * @param username 用户名
     * @param password 用户密码
     * @return 返回用户实体
     */
    User login(String username,String password);

    /**
     * 检查账户是否存在
     * @param username 账户名
     * @return 存在条数
     */
    int checkUsername(String username);

    /**
     * 查询所有用户，筛选条件
     * @param username  用户名
     * @param phone   邮箱
     * @param status 状态
     * @return 返回查询结果集
     */
    List<User> getListAdmin(String username,String phone,String status);

    List<User> getListUser(String username,String phone,String status,Integer deptId);

    /**
     * 修改user信息
     * @param user 用户实体类
     * @return 修改结果
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param userID 用户id
     * @return
     */
    int delete(Integer userID);

    String getDeptName(Integer deptId);
}
