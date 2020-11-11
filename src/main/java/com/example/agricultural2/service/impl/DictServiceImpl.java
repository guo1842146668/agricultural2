package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.Dict;
import com.example.agricultural2.mapper.DictMapper;
import com.example.agricultural2.service.IDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    @Resource
    private DictMapper dictMapper;

    @Override
    public List<Dict> getList() {
        return dictMapper.selectList(null);
    }
}
