package com.wfs.tlmarket.constants;

import lombok.Data;
import lombok.ToString;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
@Data
@ToString
public class Constants {
    /**
     * session 数据
     */
    public static final String SESSION_USER_INFO = "userInfo";

    public static final String SESSION_GOODS_LIST = "goodsListResponse";

    /**
     * 业务的返回数据
     */
    public static final String MODEL_RESPONSE = "response";

    /**
     * 存入COOKIE的 键名
     */
    public static final String COOKIE_USER_NAME = "tlUserName";
    public static final String COOKIE_PASSWORD = "tlPassword";

    /**
     * 7天的 毫秒数
     */
    public static final int SEVEN_DAY_SEC = 3600 * 24 *7;


}
