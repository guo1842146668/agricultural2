package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.Cooperative;

import java.util.List;


public interface ICooperativeService extends IService<Cooperative> {

    int add(Cooperative cooperative);

    int edit(Cooperative cooperative);

    int delete(Integer cooperativeId);

    List<Cooperative> getList(Integer page,Integer count);
}
