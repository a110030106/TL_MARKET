package com.wfs.tlmarket.service.response;

import lombok.Data;
import lombok.ToString;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
@Data
@ToString
public class Response<T> {
    private Boolean isSuccess = true;
    /**
     * errorCode 预留
     */
    private String errorCode;
    private String errorMsg;
    private T result;
}
