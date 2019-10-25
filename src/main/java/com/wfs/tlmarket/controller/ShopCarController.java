package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.models.ShopCarGoods;
import com.wfs.tlmarket.models.UserInfo;
import com.wfs.tlmarket.service.ShopCarService;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.service.response.ShopCarServiceResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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

        if (null == userNo  ||  "".equals(userNo)) {
            return new ModelAndView("login");
        }
        ShopCarGoods shopCarGoods = new ShopCarGoods();
        shopCarGoods.setUserNo(userNo);
        shopCarGoods.setGoodsNo(goodsNo);
        shopCarGoods.setGoodsCount(goodsCount);
        shopCarGoods.setGoodsPrice(goodsPrice);
        // goodsCount * goodsPrice
        shopCarGoods.setGoodsAmount(goodsPrice.multiply(goodsCount));
        Response response = shopCarService.addShopCar(shopCarGoods);

        return new ModelAndView("index");
    }

    /**
     * 进入购物车
     * @return
     */
    @RequestMapping(value = "/shopCar", method = RequestMethod.GET)
    public ModelAndView shopCar(Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Response<List<ShopCarServiceResDto>> response = shopCarService.showShopCar(userInfo.getUserNo());
        System.out.println("response : " + response);
        model.addAttribute("shopCarResponse", response);
        return new ModelAndView("shopCar");
    }

}
