package com.wfs.tlmarket.models;

import lombok.Data;
import lombok.ToString;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
@Data
@ToString
public class Response {
    private Boolean isSuccess = true;
    private String errorCode;
    private String errorMsg;
}
