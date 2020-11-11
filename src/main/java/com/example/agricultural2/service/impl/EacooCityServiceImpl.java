package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.EacooCity;
import com.example.agricultural2.mapper.EacooCityMapper;
import com.example.agricultural2.service.IEacooCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EacooCityServiceImpl extends ServiceImpl<EacooCityMapper, EacooCity> implements IEacooCityService {
   @Resource
   private EacooCityMapper eacooCityMapper;

    @Override
    public List<EacooCity> getCity(String code) {
        QueryWrapper<EacooCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prov_code",code);
        return eacooCityMapper.selectList(queryWrapper);
    }

}
