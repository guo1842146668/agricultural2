package com.example.agricultural2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.agricultural2.entity.Machinery;
import com.example.agricultural2.entity.Work;
import com.example.agricultural2.mapper.MachineryDao;
import com.example.agricultural2.mapper.WorkDao;
import com.example.agricultural2.service.WorkService;
import com.example.agricultural2.tool.CalculateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: WorkServiceImpl
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 14:28
 * @Version: 1.0
 **/
@Service
public class WorkServiceImpl extends ServiceImpl<WorkDao,Work> implements WorkService {
    @Resource
    private WorkDao workDao;
    @Resource
    private MachineryDao machineryDao;

    @Override
    public int insert(Work work) {
        return workDao.insert(work);
    }

    @Override
    public int delete(Integer workID) {
        return workDao.deleteById(workID);
    }

    @Override
    public int update(Work work) {
        return workDao.updateById(work);
    }

    @Override
    public PageInfo<Map<String,Object>> getAllAdmin(Work work,Integer page,Integer count) {
        //创建分页对象
        PageHelper.startPage(page,count);
        List<Map<String,Object>> workList = workDao.getAllAdmin(work);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(workList);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getAllUser(Work work,Integer deptId, Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Map<String,Object>> workList = workDao.getAllUser(work,deptId);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(workList);
        return pageInfo;
    }

    /*@Override
    public List<String> uploadImgs(MultipartFile[] files, Integer workID) {
        List<String> resultFile=new ArrayList<>();
        Work work=new Work();
        work.setWorkId(workID);
        List<Map<String,Object>> all = workDao.getAllAdmin();
        if(all.size() != 1){
            resultFile.add("500 请传入有效的workID");
            return resultFile;
        }
        Work workall = (Work) all.get(0);
        File flie = new File("");//"image"+File.separator+workall.().getMachineryNo()+File.separator+workall.getWorkID());
        if(flie.exists()) {
            flie.mkdir();
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            String targetUploadPath = flie + File.separator + filename;
            try {
                FileUtils.writeByteArrayToFile(new File(targetUploadPath), file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                resultFile.add("400 错误位置为："+originalFilename);
                return  resultFile;
            }
            resultFile.add("成功上传："+originalFilename);
        }

        return resultFile;
    }*/

    @Override
    public List<Map<String, Object>> upExcelAdmin() {
        List<Map<String, Object>> maps = workDao.upExcelAdmin();
        if(maps.isEmpty()){
            return  null;
        }
        return maps;
    }

    @Override
    public List<Map<String, Object>> upExcelUser(String workID,String machineryOwner) {
        List<Map<String, Object>> maps = workDao.upExcelUser(workID,machineryOwner);
        if(maps.isEmpty()){
            return  null;
        }
        return maps;
    }
}
