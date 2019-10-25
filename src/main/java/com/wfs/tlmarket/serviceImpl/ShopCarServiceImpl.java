package com.wfs.tlmarket.serviceImpl;

import com.wfs.tlmarket.mapper.GoodsInfoMapper;
import com.wfs.tlmarket.mapper.ShopCarMapper;
import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.models.ShopCarGoods;
import com.wfs.tlmarket.service.ShopCarService;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {

    @Autowired(required = false)
    private ShopCarMapper shopCarMapper;

    @Autowired(required = false)
    private GoodsInfoMapper goodsInfoMapper;



    /**
     * 显示购物车内容
     * @param userNo
     * @return
     */
    @Override
    public Response<List<ShopCarServiceResDto>> showShopCar(String userNo) {
        Response<List<ShopCarServiceResDto>> response = new Response<>();
        List<ShopCarServiceResDto> resDtoList = new ArrayList<>();
        ShopCarServiceResDto shopCarServiceResponse;
        List<ShopCarGoods> shopCarGoodsList = shopCarMapper.selectByUserNo(userNo);
        System.out.println("用户的购物车内容 : " + shopCarGoodsList);
        for (ShopCarGoods shopCarGoods : shopCarGoodsList) {
            shopCarServiceResponse = new ShopCarServiceResDto();
            shopCarServiceResponse.setShopCarGoods(shopCarGoods);
            GoodsInfo goodsInfo = goodsInfoMapper.selectByGoodsNo(shopCarGoods.getGoodsNo());
            shopCarServiceResponse.setGoodsInfo(goodsInfo);
            resDtoList.add(shopCarServiceResponse);
        }
        System.out.println("内容详情 : " + resDtoList);
        response.setResult(resDtoList);
        return response;
    }


    /**
     * 添加商品进 购物车
     * @param newShopCarGoods
     * @return
     */
    @Override
    @Transactional
    public Response addShopCar(ShopCarGoods newShopCarGoods) {
        System.out.println("添加时的 userNo ： " + newShopCarGoods.getUserNo());
        Response response = new Response();
        ShopCarGoods oldShopCarGoods =  shopCarMapper.selectByUserNoAndGoodsNo(newShopCarGoods.getUserNo(), newShopCarGoods.getGoodsNo());
        Date date = new Date();
        // 一个用户一种商品  只存在一条记录
        if (null == oldShopCarGoods) {
            // insert
            try{
                newShopCarGoods.setStatus((byte) 1);
                newShopCarGoods.setCreatedBy("王");
                newShopCarGoods.setCreatedAt(date);
                newShopCarGoods.setUpdatedBy("王");
                newShopCarGoods.setUpdatedAt(date);
                shopCarMapper.insertSelective(newShopCarGoods);
            }catch (Exception e) {
                response.setIsSuccess(false);
                response.setResult(false);
                response.setErrorMsg("网络异常，稍后再试");
            }
        }else {
            // update (合并)
            BigDecimal oldCount = oldShopCarGoods.getGoodsCount();
            BigDecimal newCount = oldCount.add(newShopCarGoods.getGoodsCount());
            // 以新单价 重新计算
            BigDecimal newPrice = newShopCarGoods.getGoodsPrice();
            BigDecimal newAmount = newPrice.multiply(newCount);
            // 构建
            newShopCarGoods.setGoodsCount(newCount);
            newShopCarGoods.setGoodsAmount(newAmount);
            newShopCarGoods.setUpdatedBy("王");
            newShopCarGoods.setUpdatedAt(date);

            int i = shopCarMapper.updateByUserNoAndGoodsNo(newShopCarGoods);
            if (i != 1) {
                response.setIsSuccess(false);
                response.setResult(false);
                response.setErrorMsg("网络异常，稍后再试");
            }
        }

        return response;
    }

}
