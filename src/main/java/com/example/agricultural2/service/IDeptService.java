package com.example.agricultural2.service;

import com.example.agricultural2.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> getListAdmin();

    List<Dept> getListUser(Integer deptId);

    int saveDept(Dept dept);

    int deleteDept(Integer deptId);

    int updateDept(Dept dept);

    int addMenu(String menu,Integer deptId);
}
