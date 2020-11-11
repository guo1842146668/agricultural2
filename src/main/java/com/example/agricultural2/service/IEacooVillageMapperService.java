package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.EacooVillage;

import java.util.List;

public interface IEacooVillageMapperService extends IService<EacooVillage> {

   List<EacooVillage> getVillage(String code);
}
