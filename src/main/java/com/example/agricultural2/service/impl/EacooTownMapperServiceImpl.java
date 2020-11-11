package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.EacooCountry;
import com.example.agricultural2.entity.EacooTown;
import com.example.agricultural2.mapper.EacooTownMapper;
import com.example.agricultural2.service.IEacooTownMapperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EacooTownMapperServiceImpl extends ServiceImpl<EacooTownMapper, EacooTown> implements IEacooTownMapperService {
   @Resource
   private EacooTownMapper eacooTownMapper;

    @Override
    public List<EacooTown> getTown(String code) {
        QueryWrapper<EacooTown> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("country_code",code);
        return eacooTownMapper.selectList(queryWrapper);
    }
}
