package com.wfs.tlmarket.mapper;


import com.wfs.tlmarket.models.UserVsOrder;

public interface UserOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserVsOrder record);

    int insertSelective(UserVsOrder record);

    UserVsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserVsOrder record);

    int updateByPrimaryKey(UserVsOrder record);
}