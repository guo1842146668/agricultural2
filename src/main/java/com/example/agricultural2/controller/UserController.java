package com.example.agricultural2.controller;


import cn.hutool.crypto.SecureUtil;
import com.example.agricultural2.common.Result;
import com.example.agricultural2.common.ResultUtil;
import com.example.agricultural2.entity.User;
import com.example.agricultural2.service.IMenuService;
import com.example.agricultural2.service.IUserService;
import com.example.agricultural2.tool.ClassIsNull;
import com.example.agricultural2.tool.JavaWebToken;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-10
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IMenuService iMenuService;
    /**
     *  登陆
     * @param username 用户名
     * @param password 密码
     * @return 登陆结果
     */
    @GetMapping("login")
    public Result login(String username, String password, HttpServletResponse response){
        User login = iUserService.login(username, SecureUtil.md5(password));
        if(ClassIsNull.isNull(login)){
            return ResultUtil.error(500,"账户或密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("user", login);
        // 根据存在用户的id生成token字符串
        String token = JavaWebToken.createJavaWebToken(m);
        map.put("token", token);
        map.put("user",login);
        if(login.getType() == 1){
            map.put("menu",iMenuService.getList());
            map.put("companyName",null);
        }else{
            map.put("menu",iMenuService.getListDept(login.getDeptId()));
            map.put("companyName",iUserService.getDeptName(login.getDeptId()));
        }
        return  ResultUtil.seccess(map);
    }

    /**
     * 查询所有用户
     * @param username 用户名
     * @param phone  邮箱
     * @param status 状态
     * @return 查询结果集
     */
    @GetMapping("list")
    public Result getList(@RequestParam(value = "page" ,defaultValue = "1")Integer page,
                          @RequestParam(value = "count" ,defaultValue = "10")Integer count,
                          String username,String phone,String status,HttpServletRequest request){
        PageHelper.startPage(page,count);
        String token = request.getHeader("token");
        Map<String, Object> map = JavaWebToken.parserJavaWebToken(token);
        User user = JavaWebToken.TokenConvertUser(map);
        List<User> listAdmin ;
        if(user.getType() == 1){
            listAdmin = iUserService.getListAdmin(username, phone, status);
        }else{
            listAdmin = iUserService.getListUser(username, phone, status, user.getDeptId());
        }
        PageInfo<User> pageInfo=new PageInfo<>(listAdmin);
        return ResultUtil.seccess(pageInfo);
    }


    /**
     * 添加用户
     * @return 添加结果
     */
    @PostMapping("add")
    public Result add(User user){
        if(ClassIsNull.isNull(user)){
            return ResultUtil.error(500,"不能添加空用户");
        }
        if(iUserService.checkUsername(user.getUsername())>0){
            return ResultUtil.error(500,"账户名不能重复");
        }
        user.setCreateTime(new Date());
        user.setType(2);
        user.setStatus(1);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        return ResultUtil.seccess(iUserService.saveUser(user));
    }

    /**
     * 为用户添加分组
     * @param userId 用户ID
     * @param deptId 分组ID
     * @return 添加结果
     */
    @PostMapping("/addDept")
    public Result addDept(Integer userId,Integer deptId){
        User user = new User();
        user.setUserId(userId);
        user.setDeptId(deptId);
        return ResultUtil.seccess(iUserService.updateUser(user));
    }

    /**
     * 修改用户
     * @param user 分组ID
     * @return 添加结果
     */
    @PostMapping("/update")
    public Result update(User user){
        if(user.getPassword() != null){
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        return ResultUtil.seccess(iUserService.updateUser(user));
    }

    /**
     * 禁用用户
     * @param user 分组ID
     * @return 添加结果
     */
    @PostMapping("/disable")
    public Result Disable(User user){
        return ResultUtil.seccess(iUserService.updateUser(user));
    }


    /**
     * @Author guoyangyang
     * @Description  更换密码
     * @Date  2020/7/21 18:53
     * @Param * @param userName: 用户名
     * @param passWord: 原来的密码
     * @param newPassWord: 新密码
     * @return * @return: com.puyan.shengren.agricultural.common.ServerResponse<java.lang.String>
     *     返回更换结果成功或失败
     **/
    @ApiOperation(value="修改密码", notes="修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true ,dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "旧密码", required = true ,dataType = "String"),
            @ApiImplicitParam(name = "newPassWord", value = "新密码", required = true ,dataType = "String")
    })
    @PutMapping("/forgetResetPassword")
    @ResponseBody
    public  Result forgetResetPassword(@Param("userName") String userName, @Param("passWord") String passWord, @Param("newPassWord") String newPassWord){
        User login = iUserService.login(userName, SecureUtil.md5(passWord));
        if(ClassIsNull.isNull(login)){
            return ResultUtil.error(500,"账户或密码错误");
        }
        login.setPassword(SecureUtil.md5(newPassWord));
        return ResultUtil.seccess(iUserService.updateUser(login));
    }

    /**
     * @Author guoyangyang
     * @Description  根据用户ID获取对应权限
     * @Date  2020/7/22 15:43
     * @Param * @param userID:
     * @return * @return: com.puyan.shengren.agricultural.common.Result
     **/
    @ApiOperation(value="根据用户ID获取对应权限", notes="根据用户ID获取对应权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户ID", required = true ,dataType = "Integer"),
    })
    @GetMapping("/getFunction")
    @ResponseBody
    public Result getFunctionByUserID(Integer userID){
        return ResultUtil.seccess(iMenuService.getListDept(userID));
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteUser(Integer userID){
        if(userID == 1){
            return  ResultUtil.error(400,"超级管理员不能删除");
        }
        return ResultUtil.seccess(iUserService.delete(userID));
    }
}
