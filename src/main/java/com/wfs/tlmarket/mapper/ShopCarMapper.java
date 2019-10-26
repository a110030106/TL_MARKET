package com.wfs.tlmarket.mapper;


import com.wfs.tlmarket.models.ShopCarGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopCarGoods record);

    int insertSelective(ShopCarGoods record);

    ShopCarGoods selectByPrimaryKey(Integer id);

    ShopCarGoods selectByUserNoAndGoodsNo(@Param("userNo") String userNo, @Param("goodsNo") String goodsNo);

    List<ShopCarGoods> selectByUserNo(String userNo);

    int updateByUserNoAndGoodsNo(ShopCarGoods record);
    int updateDelete(@Param("userNo") String userNo, @Param("goodsNo") String goodsNo);

    int updateByPrimaryKeySelective(ShopCarGoods record);
    int updateByPrimaryKey(ShopCarGoods record);
}