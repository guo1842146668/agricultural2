package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.common.Result;
import com.example.agricultural2.entity.Region;
import com.example.agricultural2.mapper.RegionMapper;
import com.example.agricultural2.service.IRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
    @Resource
    private RegionMapper regionMapper;

    @Override
    public List<Region> getProvince() {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level",1);
        return regionMapper.selectList(queryWrapper);
    }

    @Override
    public List<Region> getSubordinate(Integer pid) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        return regionMapper.selectList(queryWrapper);
    }

}
