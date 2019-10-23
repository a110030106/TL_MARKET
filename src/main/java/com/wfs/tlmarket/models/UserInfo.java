package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserInfo {
    private Integer id;

    private String userNo;

    private String userName;

    private String password;

    private String userNickName;

    private String userIcon;

    private String phone;

    private Byte authority;

    private Integer integral;

    private Integer orderSuccessCount;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

}