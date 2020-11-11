package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.Cooperative;
import com.example.agricultural2.mapper.CooperativeMapper;
import com.example.agricultural2.service.ICooperativeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CooperativeServiceImpl extends ServiceImpl<CooperativeMapper,Cooperative> implements ICooperativeService {
    @Resource
    private CooperativeMapper cooperativeMapper;

    @Override
    public int add(Cooperative cooperative) {
        return cooperativeMapper.insert(cooperative);
    }

    @Override
    public int edit(Cooperative cooperative) {
        return cooperativeMapper.updateById(cooperative);
    }

    @Override
    public int delete(Integer cooperativeId) {
        return cooperativeMapper.deleteById(cooperativeId);
    }

    @Override
    public List<Cooperative> getList(Integer page,Integer count) {
        PageHelper.startPage(page,count);
        return cooperativeMapper.selectList(null);
    }
}
