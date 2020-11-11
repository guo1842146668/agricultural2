package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/region")
public class RegionController {
    //省
    @Resource
    private IEacooProvMapperService iEacooProvMapperService;
    //市
    @Resource
    private IEacooCityService iEacooCityService;
    //区
    @Resource
    private IEacooCountryService iEacooCountryService;
    //镇
    @Resource
    private IEacooTownMapperService iEacooTownMapperService;
    //村
    @Resource
    private IEacooVillageMapperService iEacooVillageMapperService;

    @GetMapping("/province")
    public  Result getProvince(){
        return ResultUtil.seccess(iEacooProvMapperService.getProvince());
    }

    @GetMapping("/city")
    public  Result getCity(String code){
        return ResultUtil.seccess(iEacooCityService.getCity(code));
    }

    @GetMapping("/Country")
    public  Result getCountry(String code){
        return ResultUtil.seccess(iEacooCountryService.getCountry(code));
    }

    @GetMapping("/town")
    public  Result getTown(String code){
        return ResultUtil.seccess(iEacooTownMapperService.getTown(code));
    }

    @GetMapping("/village")
    public  Result getVillage(String code){
        return ResultUtil.seccess(iEacooVillageMapperService.getVillage(code));
    }

}
