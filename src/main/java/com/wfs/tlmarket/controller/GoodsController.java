package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.GoodsService;
import com.wfs.tlmarket.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public ModelAndView searchGoods(String searchName, Model model) {

        Response<List<GoodsInfo>> response = goodsService.searchGoods(searchName);
        model.addAttribute("response",response);
        return new ModelAndView("index");
    }

}
