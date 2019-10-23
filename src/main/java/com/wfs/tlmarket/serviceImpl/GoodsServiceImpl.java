package com.wfs.tlmarket.serviceImpl;

import com.wfs.tlmarket.mapper.GoodsInfoMapper;
import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.GoodsService;
import com.wfs.tlmarket.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public Response<List<GoodsInfo>> searchGoods(String searchName) {
        Response<List<GoodsInfo>> response = new Response<>();
        String likeSearchName = "%" + searchName + "%";
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectByGoodsName(likeSearchName);
        if (null != goodsInfoList  &&  0 != goodsInfoList.size()) {
            response.setResult(goodsInfoList);
        }else {
            response.setErrorMsg("未找到相关搜索");
        }
        return response;
    }
}
