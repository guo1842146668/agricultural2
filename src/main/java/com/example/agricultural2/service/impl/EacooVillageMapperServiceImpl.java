package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.EacooTown;
import com.example.agricultural2.entity.EacooVillage;
import com.example.agricultural2.mapper.EacooVillageMapper;
import com.example.agricultural2.service.IEacooVillageMapperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EacooVillageMapperServiceImpl extends ServiceImpl<EacooVillageMapper, EacooVillage> implements IEacooVillageMapperService {
    @Resource
    private  EacooVillageMapper eacooVillageMapper;
    @Override
    public List<EacooVillage> getVillage(String code) {
        QueryWrapper<EacooVillage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("town_code",code);
        return eacooVillageMapper.selectList(queryWrapper);
    }
}
