package com.example.agricultural2.controller;


import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.service.IMenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService iMenuService;

    /**
     * 查询所有菜单权限
     * @return 返回查询结果集
     */
    @GetMapping("list")
    public Result getList(){
        return ResultUtil.seccess(iMenuService.getList());
    }

}
