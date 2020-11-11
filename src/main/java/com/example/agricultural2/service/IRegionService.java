package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.Region;
import java.util.List;

public interface IRegionService extends IService<Region> {

    List<Region> getProvince();

    List<Region> getSubordinate(Integer pid);


}
