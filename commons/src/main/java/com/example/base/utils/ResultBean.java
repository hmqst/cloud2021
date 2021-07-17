package com.example.base.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> implements Serializable {

    public static final int SUCCESS = 0;

    public static final int FAIL = -1;

    public static final String SUCCESS_MESSAGE = "成功";

    public static final String FAIL_MESSAGE = "失败";

    /**
     * 返回码，全局默认成功码为0，失败为-1
     */
    private int code;

    /**
     * 返回错误信息
     */
    private String message;

    /**
     * 返回对象，可以是简单对象，封装处理后的对象，或者MAP
     */
    private T object;

    public static <T> ResultBean<T> success() {
        return new ResultBean<T>(SUCCESS, SUCCESS_MESSAGE, null);
    }

    public static <T> ResultBean<T> success(T object) {
        if (object instanceof String){
            return new ResultBean<T>(SUCCESS, (String) object, null);
        }
        return new ResultBean<T>(SUCCESS, SUCCESS_MESSAGE, object);
    }

    public static <T> ResultBean<T> success(String message) {
        return new ResultBean<T>(SUCCESS, message, null);
    }

    public static <T> ResultBean<T> success(String message, T object) {
        return new ResultBean<T>(SUCCESS, message, object);
    }

    public static <T> ResultBean<T> fail() {
        return new ResultBean<T>(FAIL, FAIL_MESSAGE, null);
    }

    public static <T> ResultBean<T> fail(String message) {
        return new ResultBean<T>(FAIL, message, null);
    }

    public static <T> ResultBean<T> fail(Integer code, String message) {
        return new ResultBean<T>(code, message, null);
    }

}
