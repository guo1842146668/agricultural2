package com.example.agricultural2.service.impl;

import com.example.agricultural2.entity.Menu;
import com.example.agricultural2.mapper.MenuMapper;
import com.example.agricultural2.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getList() {
        return menuMapper.selectList(null);
    }

    @Override
    public List<Menu> getListDept(Integer deptId) {
        return menuMapper.getListDept(deptId);
    }
}
