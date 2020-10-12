package com.example.agricultural2.common;

/**
 * @ClassName: ResultUtil
 * @Description: TODO
 * @Author: idmin
 * @Date: 2020/7/30 17:31
 * @Version: 1.0
 **/
public class ResultUtil {
        public static Result seccess(Object object){
            Result result = new Result();
            result.setCode(200);
            result.setMsg("操作成功");
            result.setData(object);
            return result;
        }

        public static Result seccess(){
            return seccess(null);
        }

        public static Result error(Integer code,String msg){
            Result result=new Result();
            result.setCode(code);
            result.setMsg(msg);
            return result;
        }
}
