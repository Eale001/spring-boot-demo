package com.eale.rpc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Admin
 * @Date 2020/8/19
 * @Description
 * @Version 1.0
 **/
@Data
public class Request implements Serializable {

    private String className;

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] parameters;

}
