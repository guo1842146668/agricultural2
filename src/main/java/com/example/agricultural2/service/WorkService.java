package com.example.agricultural2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.agricultural2.entity.Work;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkService
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/23 14:27
 * @Version: 1.0
 **/
public interface WorkService extends IService<Work> {

    int insert(Work work);

    int delete(Integer workID);

    int update(Work work);

    PageInfo<Map<String,Object>> getAllAdmin(Work work,Integer page, Integer count);

    PageInfo<Map<String,Object>> getAllUser(Work work,Integer deptId, Integer page, Integer count);

    /*List<String> uploadImgs(@RequestParam("file") MultipartFile[] files, Integer workID);*/

    List<Map<String,Object>> upExcelAdmin();

    List<Map<String,Object>> upExcelUser(String workID,String machineryOwner);
}
