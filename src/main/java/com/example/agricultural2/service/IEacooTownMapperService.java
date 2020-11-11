package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.EacooTown;

import java.util.List;

public interface IEacooTownMapperService extends IService<EacooTown> {

   List<EacooTown> getTown(String code);
}
