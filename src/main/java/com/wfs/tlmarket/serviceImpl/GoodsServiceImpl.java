package com.wfs.tlmarket.serviceImpl;

import com.wfs.tlmarket.mapper.GoodsInfoMapper;
import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.GoodsService;
import com.wfs.tlmarket.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 搜索  todo   用es工具 搭建此服务
     * @param searchName
     * @return
     */
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

    /**
     * 按商品类型 返回 商品list
     * @param goodsType
     * @return
     */
    @Override
    public Response<List<GoodsInfo>> selectGoodsInfoList(int goodsType) {
        Response<List<GoodsInfo>> response = new Response<>();
        List<GoodsInfo> goodsInfoList;
        // 为0时  为 首页显示
        if (0 == goodsType) {
            // 热门商品 与 最新商品
            goodsInfoList = goodsInfoMapper.selectByIsHot(1);
            List<GoodsInfo> newGoodsList = goodsInfoMapper.selectByIsNew(1);
            goodsInfoList.addAll(newGoodsList);
        }else {
            goodsInfoList = goodsInfoMapper.selectByGoodsType(goodsType);
            if (null == goodsInfoList  &&  0 == goodsInfoList.size()) {
                response.setIsSuccess(false);
                response.setErrorMsg("该类暂无产品");
                return response;
            }
        }
        response.setResult(goodsInfoList);
        return response;
    }


}
