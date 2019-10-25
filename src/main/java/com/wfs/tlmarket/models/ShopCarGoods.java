package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class ShopCarGoods {
    private Integer id;

    private String userNo;

    private String goodsNo;

    private BigDecimal goodsPrice;

    private BigDecimal goodsCount;

    private BigDecimal goodsAmount;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}