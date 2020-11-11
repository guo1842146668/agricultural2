package com.example.agricultural2.controller;

import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.entity.Cooperative;
import com.example.agricultural2.service.ICooperativeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cooperative")
public class CooperativeController {
    @Resource
    private ICooperativeService iCooperativeService;

    /**
     * 添加合作社信息
     * @param cooperative
     * @return
     */
    @PostMapping("/add")
    public Result add(Cooperative cooperative){
        return ResultUtil.seccess(iCooperativeService.add(cooperative));
    }

    /**
     * 修改合作社信息
     * @param cooperative
     * @return
     */
    @PutMapping("/edit")
    public Result edit(Cooperative cooperative){
        return ResultUtil.seccess(iCooperativeService.edit(cooperative));
    }

    /**
     * 查询合作社信息
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                       @RequestParam(value = "count",defaultValue = "10") Integer count){
        List<Cooperative> list = iCooperativeService.getList(page, count);
        PageInfo<Cooperative> pageInfo = new PageInfo<>(list);
        return ResultUtil.seccess(pageInfo);
    }

    @DeleteMapping("/delete")
    public Result list(Integer cooperativeId){
        return  ResultUtil.seccess(iCooperativeService.delete(cooperativeId));
    }

}
