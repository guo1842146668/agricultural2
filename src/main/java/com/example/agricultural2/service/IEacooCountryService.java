package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.EacooCountry;

import java.util.List;

public interface IEacooCountryService extends IService<EacooCountry> {

   List<EacooCountry> getCountry(String code);
}
