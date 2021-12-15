package com.liangzhicheng.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

}
