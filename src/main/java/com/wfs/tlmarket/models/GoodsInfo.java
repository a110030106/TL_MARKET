package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GoodsInfo {
    private Integer id;

    private String goodsNo;

    private String goodsName;

    private String goodsType;

    private String goodsSpecify;

    private Integer goodsPrice;

    private String goodsPhtot;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}