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

    private String status;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscountsNo() {
        return discountsNo;
    }

    public void setDiscountsNo(String discountsNo) {
        this.discountsNo = discountsNo == null ? null : discountsNo.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getDiscountsType() {
        return discountsType;
    }

    public void setDiscountsType(String discountsType) {
        this.discountsType = discountsType == null ? null : discountsType.trim();
    }

    public Integer getDiscountsPrice() {
        return discountsPrice;
    }

    public void setDiscountsPrice(Integer discountsPrice) {
        this.discountsPrice = discountsPrice;
    }

    public Integer getDiscountsRemark() {
        return discountsRemark;
    }

    public void setDiscountsRemark(Integer discountsRemark) {
        this.discountsRemark = discountsRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}