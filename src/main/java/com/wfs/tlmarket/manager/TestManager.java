package com.wfs.tlmarket.manager;

import com.wfs.tlmarket.mapper.UserOrderMapper;
import com.wfs.tlmarket.models.UserInfo;
import com.wfs.tlmarket.models.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建人：王福顺  创建时间：2019/10/23
 */
@Component
public class TestManager {

    @Autowired(required = false)
    private UserOrderMapper userOrderMapper;

    public UserOrder getUserOrder() {
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(1);
         return userOrder;
    }
}
