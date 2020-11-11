package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.EacooProv;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IEacooProvMapperService extends IService<EacooProv> {

    List<EacooProv> getProvince();
}
