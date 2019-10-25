package com.wfs.tlmarket.mapper;


import com.wfs.tlmarket.models.UserInfo;

import java.util.Date;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByUserName(String userName);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

}