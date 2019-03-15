package com.liam.oauth2.util;

/**
 * @author: liangzy
 * @date: 2019/03/15 下午4:56
 * @desc:
 */
public class StringUtil {

    public static boolean isEmpty(Object str){
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object str){
        return !isEmpty(str);
    }
}
