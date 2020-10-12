package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.agricultural2.entity.Dept;
import com.example.agricultural2.mapper.DeptMapper;
import com.example.agricultural2.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getListAdmin() {
        return deptMapper.selectList(null);
    }

    @Override
    public List<Dept> getListUser(Integer deptId) {
        return deptMapper.getListUser(deptId);
    }

    @Override
    public int saveDept(Dept dept) {
        return deptMapper.insert(dept);
    }

    @Override
    public int deleteDept(Integer deptId) {
        QueryWrapper<Dept> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("parent_id",deptId);
        List<Dept> depts = deptMapper.selectList(queryWrapper);
        if(depts.size() == 0){
            return deptMapper.deleteById(deptId);
        }else{
            for (int i = 0; i < depts.size(); i++) {
                return deptMapper.deleteById(depts.get(i).getDeptId());
            }
            return 1;
        }
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateById(dept);
    }

    @Override
    public int addMenu(String menu, Integer deptId) {
         deptMapper.deleteByDeptId(deptId);
         String[] array=menu.split(",");
        for (int i = 0; i < array.length; i++) {
            deptMapper.insertDeptMenu(deptId,Integer.parseInt(array[i]));
        }
        return array.length;
    }
}
