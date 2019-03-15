package com.liam.oauth2.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: liangzy
 * @date: 18-8-16 下午2:25
 * @desc: 客户端类
 */
@Data
public class Client {
    private Integer id;
    private Timestamp inserttime;
    private Timestamp updatetime;
    private String client_id;
    private String resource_ids;
    private String client_secret;
    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private long access_token_validity;
    private long refresh_token_validity;
    private String additional_information;
    private String autoapprove;
}
