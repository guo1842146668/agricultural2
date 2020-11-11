package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.Dict;

import java.util.List;


public interface IDictService extends IService<Dict> {

    List<Dict> getList();
}
