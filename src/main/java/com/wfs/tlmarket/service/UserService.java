package com.wfs.tlmarket.service;

import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.models.UserInfo;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
public interface UserService {

    /**
     * 注册
     * @param userInfo
     * @return
     */
    public Response register(UserInfo userInfo);

    /**
     * 登录 鉴权
     * @param userInfo
     * @return
     */
    public Response auth(UserInfo userInfo);
}
