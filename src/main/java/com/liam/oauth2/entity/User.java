package com.liam.oauth2.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: liangzy
 * @date: 18-8-16 下午2:25
 * @desc: 用户类
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Timestamp inserttime;
    private Timestamp updatetime;
}
