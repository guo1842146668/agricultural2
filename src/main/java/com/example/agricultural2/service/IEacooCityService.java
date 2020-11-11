package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.EacooCity;

import java.util.List;

public interface IEacooCityService extends IService<EacooCity> {
    List<EacooCity> getCity(String code);
}
