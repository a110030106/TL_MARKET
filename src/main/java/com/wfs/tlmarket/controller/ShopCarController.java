package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.models.UserInfo;
import com.wfs.tlmarket.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 创建人：王福顺  创建时间：2019/10/24
 */
@RestController
public class ShopCarController {

    @Autowired
    private ShopCarService shopCarService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/shopCar", method = RequestMethod.GET)
    public ModelAndView shopCar() {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

        return new ModelAndView("shopCar");
    }

}
