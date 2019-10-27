package com.wfs.tlmarket.serviceImpl;

import com.wfs.tlmarket.models.OrderInfo;
import com.wfs.tlmarket.service.OrderService;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/27
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 创建订单
     * @param shopCarServiceResDtoList
     * @return
     */
    @Override
    public Response createOrder(String userNo, List<ShopCarServiceResDto> shopCarServiceResDtoList) {
        // 1生成订单
        OrderInfo orderInfo;
        for (ShopCarServiceResDto carServiceResDto : shopCarServiceResDtoList) {
            orderInfo = new OrderInfo();
            orderInfo.setGoodsNo(carServiceResDto.getGoodsNo());
            orderInfo.setUserNo(userNo);
            orderInfo.setGoodsCount(carServiceResDto.getGoodsCount());
            orderInfo.setGoodsPrice(carServiceResDto.getGoodsPrice());
            // todo  是否有用户
//            orderInfo.setOrderNo();
        }


        // 2删除购物车内容
        // 3下单提醒
        return null;
    }
}
