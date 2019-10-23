package com.wfs.tlmarket.service;

import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.response.Response;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
public interface GoodsService {

    public Response<List<GoodsInfo>> searchGoods(String searchName);
}
