package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.EacooProv;
import com.example.agricultural2.mapper.EacooProvMapper;
import com.example.agricultural2.service.IEacooProvMapperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EacooProvMapperServiceImpl extends ServiceImpl<EacooProvMapper, EacooProv> implements IEacooProvMapperService {

    @Resource
    private EacooProvMapper eacooProvMapper;

    @Override
    public List<EacooProv> getProvince() {
        return eacooProvMapper.selectList(null);
    }
}
