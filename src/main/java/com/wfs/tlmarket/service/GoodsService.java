package com.wfs.tlmarket.service;

import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.response.Response;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
public interface GoodsService {

    /**
     * 搜索商品
     * @param searchName
     * @return
     */
    public Response<List<GoodsInfo>> searchGoods(String searchName);

    /**
     * 根据类别查询
     * @param goodsType
     * @return
     */
    public Response<List<GoodsInfo>> selectGoodsInfoList(int goodsType);

}
