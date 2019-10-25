package com.wfs.tlmarket.service.response;

import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.models.ShopCarGoods;
import lombok.Data;
import lombok.ToString;

/**
 * 创建人：王福顺  创建时间：2019/10/25
 */
@Data
@ToString
public class ShopCarServiceResDto {
    private ShopCarGoods shopCarGoods;
    private GoodsInfo goodsInfo;
}
