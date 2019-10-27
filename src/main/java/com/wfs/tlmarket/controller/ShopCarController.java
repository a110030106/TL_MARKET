package com.wfs.tlmarket.controller;


import com.google.gson.reflect.TypeToken;
import com.wfs.tlmarket.constants.Constants;
import com.wfs.tlmarket.models.OrderInfo;
import com.wfs.tlmarket.models.ShopCarGoods;
import com.wfs.tlmarket.models.UserInfo;
import com.wfs.tlmarket.service.OrderService;
import com.wfs.tlmarket.service.ShopCarService;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;
import com.wfs.tlmarket.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
@RestController
public class ShopCarController {

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpSession session;


    /**
     * 购物车添加商品
     * @param userNo
     * @param goodsNo
     * @param goodsCount
     * @param goodsPrice
     * @return
     */
    @RequestMapping(value = "/addShopCar", method = RequestMethod.GET)
    public ModelAndView addShopCar(String userNo, String goodsNo, BigDecimal goodsCount, BigDecimal goodsPrice, Model model) {
        Response response = new Response();
        ModelAndView modelAndView = new ModelAndView();
        if (null == userNo  ||  "".equals(userNo)) {
            // todo 如果未登录 添加商品
            // todo 则  1、新建用户信息（包括 用户编号、用户名（游客_ + 昵称）、昵称、权限（5）））
            // todo     2、新建购物车信息
            response.setErrorMsg("未登录");
            modelAndView.setViewName("login");
        }
        if (goodsCount == new BigDecimal("0")) {
            response.setErrorMsg("添加商品数量为 0");
            modelAndView.setViewName("index");
        }
        ShopCarGoods shopCarGoods = new ShopCarGoods();
        shopCarGoods.setUserNo(userNo);
        shopCarGoods.setGoodsNo(goodsNo);
        shopCarGoods.setGoodsCount(goodsCount);
        shopCarGoods.setGoodsPrice(goodsPrice);
        // goodsCount * goodsPrice
        shopCarGoods.setGoodsAmount(goodsPrice.multiply(goodsCount));
        response = shopCarService.addShopCar(shopCarGoods);
        return new ModelAndView("index");
    }

    /**
     * 删除购物车 商品
     * @param goodsNo
     * @return
     */
    @RequestMapping(value = "/deleteGoods")
    public ModelAndView deleteGoods(String goodsNo) {
        System.out.println("goodsNo : " + goodsNo);
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        String userNo = userInfo.getUserNo();
        Response response = shopCarService.deleteGoodsByShopCar(userNo, goodsNo);
        return new ModelAndView("redirect:/shopCar");
    }

    /**
     * 进入购物车
     * @return
     */
    @RequestMapping(value = "/shopCar", method = RequestMethod.GET)
    public ModelAndView shopCar(Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Response<List<ShopCarServiceResDto>> response = shopCarService.showShopCar(userInfo.getUserNo());
        model.addAttribute("shopCarResponse", response);
        return new ModelAndView("shopCar");
    }

    /**
     * 买单 结算
     * @param shopCarServiceResDto
     * @return
     */
    @RequestMapping(value = "/closeAccount", method = RequestMethod.POST)
    public ModelAndView closeAccount(String shopCarServiceResDto, Model model) {
        // 解析 json
        List<ShopCarServiceResDto> shopCarServiceResDtoList = GsonUtil.fromJson(shopCarServiceResDto, new TypeToken<List<ShopCarServiceResDto>>() {}.getType());
        System.out.println("看看： " + shopCarServiceResDtoList);
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_INFO);
        Response response = orderService.createOrder(userInfo.getUserNo(), shopCarServiceResDtoList);
        model.addAttribute(Constants.MODEL_RESPONSE, response);
        ModelAndView modelAndView = new ModelAndView();
        if (!response.getIsSuccess()) {
            modelAndView.setViewName("shopCar");
            return modelAndView;
        }
        modelAndView.setViewName("myOrders");
        return modelAndView;
    }

}
