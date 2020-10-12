package com.example.agricultural2.service;

import com.example.agricultural2.entity.Menu;
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
public interface IMenuService extends IService<Menu> {

    List<Menu> getList();

    List<Menu> getListDept(Integer deptId);
}
