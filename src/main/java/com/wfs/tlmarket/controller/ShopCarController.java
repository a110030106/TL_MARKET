package com.wfs.tlmarket.controller;


import com.google.gson.reflect.TypeToken;
import com.wfs.tlmarket.models.ShopCarGoods;
import com.wfs.tlmarket.models.UserInfo;
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
    public ModelAndView closeAccount(String shopCarServiceResDto) {
        System.out.println("1 ： " + shopCarServiceResDto);
        List<ShopCarServiceResDto> shopCarServiceResDtoList = GsonUtil.fromJson(shopCarServiceResDto, new TypeToken<List<ShopCarServiceResDto>>() {}.getType());
        System.out.println("看看： " + shopCarServiceResDtoList);
        // todo   doing
        // 1生成订单
        // 2删除购物车内容
        // 3下单提醒
        return null;
    }

}
