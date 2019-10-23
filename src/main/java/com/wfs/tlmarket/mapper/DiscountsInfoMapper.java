package com.wfs.tlmarket.mapper;


import com.wfs.tlmarket.models.DiscountsInfo;

public interface DiscountsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiscountsInfo record);

    int insertSelective(DiscountsInfo record);

    DiscountsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiscountsInfo record);

    int updateByPrimaryKey(DiscountsInfo record);
}