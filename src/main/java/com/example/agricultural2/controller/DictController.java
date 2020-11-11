package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.service.IDictService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/dict")
public class DictController {
    @Resource
    private IDictService iDictService;

    @GetMapping("list")
    public Result getList(){
        return ResultUtil.seccess(iDictService.getList());
    }
}
