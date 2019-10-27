package com.wfs.tlmarket.service;

import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/27
 */
public interface OrderService {

    /**
     * 生成订单
     * @return
     */
    public Response createOrder(String userNo, List<ShopCarServiceResDto> shopCarServiceResDtoList);
}
