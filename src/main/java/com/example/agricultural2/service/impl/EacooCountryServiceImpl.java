package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.EacooCity;
import com.example.agricultural2.entity.EacooCountry;
import com.example.agricultural2.mapper.EacooCountryMapper;
import com.example.agricultural2.service.IEacooCountryService;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EacooCountryServiceImpl extends ServiceImpl<EacooCountryMapper, EacooCountry> implements IEacooCountryService {
    @Resource
    private EacooCountryMapper eacooCountryMapper;

    @Override
    public List<EacooCountry> getCountry(String code) {
        QueryWrapper<EacooCountry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",code);
        return eacooCountryMapper.selectList(queryWrapper);
    }
}
