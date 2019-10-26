package com.wfs.tlmarket.service.response;

import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.models.ShopCarGoods;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 创建人：王福顺  创建时间：2019/10/25
 */
@Data
@ToString
public class ShopCarServiceResDto {

    /**
     * 购物车的 数量、总价
     */
    private BigDecimal goodsCount;

    private BigDecimal goodsAmount;

    /**
     * 商品的 名称、规格、价格
     */
    private String goodsNo;

    private String goodsName;

    private String goodsSpecify;

    private BigDecimal goodsPrice;

    /**
     * 确认订单时，必须要有电话。todo 如果未登录 ， 需要发验证码 才可以下单（防止恶意 批量下单）
     */
    private String orderPhone;
}
