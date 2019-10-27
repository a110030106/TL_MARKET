package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class UserVsOrder {
    private Integer id;

    private String orderNo;

    private String userNo;

    private Boolean orderStatus;

    private String orderDiscountsNo;

    private BigDecimal orderAmount;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}