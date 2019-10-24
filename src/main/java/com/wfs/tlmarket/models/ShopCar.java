package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ShopCar {
    private Integer id;

    private String userNo;

    private String goodsNo;

    private Integer goodsPrice;

    private Integer goodsCount;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}