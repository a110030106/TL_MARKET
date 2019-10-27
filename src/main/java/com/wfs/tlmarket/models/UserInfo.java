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

    /**
     * 5游客：无法持有积分与优惠券；
     * 4注册用户：可以有积分与优惠券；
     * 3：vip：功能待定；
     * 2：待定：。...；
     * 1：管理员 管理商品，上下架，以及订单、用户的  增删改查
     * 0：超级管理员， 可以操作 数据库 delete， 及项目所有信息
     */
    private Byte authority;

    private Integer integral;

    private Integer orderSuccessCount;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

}