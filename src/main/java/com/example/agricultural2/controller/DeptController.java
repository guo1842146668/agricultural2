package com.example.agricultural2.controller;


import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.entity.Dept;
import com.example.agricultural2.entity.User;
import com.example.agricultural2.service.IDeptService;
import com.example.agricultural2.tool.ClassIsNull;
import com.example.agricultural2.tool.JavaWebToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private IDeptService iDeptService;

    /**
     * 查询所有分组
     * @param request token
     * @return 返回查询结果集
     */
    @GetMapping("/list")
    public Result getList(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        if(user.getType() == 1){
        return ResultUtil.seccess(iDeptService.getListAdmin());
        }else{
            if(user.getDeptId() == null){
                return  ResultUtil.seccess();
            }else{
                return ResultUtil.seccess(iDeptService.getListUser(user.getDeptId()));
            }
        }
    }

    /**
     * 添加分组
     * @param dept 分组实体类
     * @return 返回添加结果
     */
    @PostMapping("/add")
    public Result add(Dept dept){
        if(ClassIsNull.isNull(dept)){
            return  ResultUtil.error(500,"添加信息不能为空");
        }
        if(null == dept.getParentId()){
            return  ResultUtil.error(500,"父级ID不能为空");
        }
        return  ResultUtil.seccess(iDeptService.saveDept(dept));
    }

    /**
     * 删除分组信息
     * @param deptId  分组ID
     * @return 返回删除结果
     */
    @DeleteMapping("/delete")
    public Result delete(Integer deptId){
        if(null == deptId){
            return  ResultUtil.error(500,"子节点ID不能为空");
        }
        return  ResultUtil.seccess(iDeptService.deleteDept(deptId));
    }

    /**
     * 修改分组信息
     * @param dept 分组实体类
     * @return 返回修改结果
     */
    @PutMapping("/update")
    public Result update(Dept dept){
        if(null == dept.getDeptId()){
            return  ResultUtil.error(500,"子节点ID不能为空");
        }
        return  ResultUtil.seccess(iDeptService.updateDept(dept));
    }

    /**
     * 给组成员赋予权限
     * @param menu 权限菜单ID集合
     * @param deptId 分组ID
     * @return 返回赋予结果
     */
    @PostMapping("/addMenu")
    public Result addMenu(String menu,Integer deptId){
        return ResultUtil.seccess(iDeptService.addMenu(menu,deptId));
    }
}
