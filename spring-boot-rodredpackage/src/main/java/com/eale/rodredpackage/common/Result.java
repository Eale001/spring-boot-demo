package com.eale.rodredpackage.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description
 * @Version 1.0
 **/
@Data
public class Result<T> implements Serializable {


    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static Result success(){
        return new Result(0,"success",null);
    }

    public Result buildSuccess(T data){
        return new Result<T>(0,"success",data);
    }

}
