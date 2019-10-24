package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class DiscountsInfo {
    private Integer id;

    private String discountsNo;

    private String userNo;

    private String discountsType;

    private Integer discountsPrice;

    private Integer discountsRemark;

    private Byte status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}