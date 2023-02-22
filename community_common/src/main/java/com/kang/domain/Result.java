package com.kang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 康
 * @CreateDate 2023/2/21
 */
@Data
@AllArgsConstructor
public class Result {

    private int code;
    private String msg;
    private Object data;

    /**
     * 构造方法私有化
     */
    private Result(){}

    public static Result result(int code,String msg,Object data){
        return new Result(code,msg,data);
    }

    /**
     * 成功 返回结果
     * @return
     */
    public static Result success(){
        return result(200,"成功!",null);
    }

    public static Result success(String msg){
        return result(200,msg,null);
    }

    public static Result success(Object data){
        return result(200,"成功!",data);
    }


    /**
     * 失败 返回结果
     */
    public static Result error(){
        return result(400,"失败!",null);
    }

    public static Result error(String msg){
        return result(400,msg,null);
    }

    public static Result error(Object data){
        return result(400,"失败!",data);
    }

    public static Result error(int code,String msg){
        return result(code,msg,null);
    }


}
