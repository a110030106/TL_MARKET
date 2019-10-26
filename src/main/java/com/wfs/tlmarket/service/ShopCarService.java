package com.wfs.tlmarket.service;

import com.wfs.tlmarket.models.ShopCarGoods;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
@Service
public interface ShopCarService {

    /**
     * 添加商品进购物车
     * @param shopCarGoods
     * @return
     */
    public Response addShopCar(ShopCarGoods shopCarGoods);

    /**
     * 显示购物车内容
     * @param userNo
     * @return
     */
    public Response<List<ShopCarServiceResDto>> showShopCar(String userNo);

    /**
     * 删除购物车内的 商品
     * @param userNo
     * @param goodsNo
     * @return
     */
    public Response deleteGoodsByShopCar(String userNo, String goodsNo);

    /**
     * 结算购物车
     * @param shopCarGoodsList
     * @return
     */
    public Response closeAccount(List<ShopCarGoods> shopCarGoodsList);


}
