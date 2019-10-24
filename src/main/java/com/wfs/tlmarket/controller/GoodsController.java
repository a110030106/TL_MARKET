package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.constants.Constants;
import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.GoodsService;
import com.wfs.tlmarket.service.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by 王福顺  on 2019/10/23
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/search", method= RequestMethod.GET)
    public ModelAndView searchGoods(String searchName) {

        Response<List<GoodsInfo>> response = goodsService.searchGoods(searchName);
        session.setAttribute(Constants.SESSION_GOODS_LIST,response);

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/findGoodsList", method = RequestMethod.GET)
    public ModelAndView findGoodsList(int goodsType) {

        Response<List<GoodsInfo>> goodsInfoList = goodsService.selectGoodsInfoList(goodsType);

        session.setAttribute(Constants.SESSION_GOODS_LIST, goodsInfoList);

        return new ModelAndView("index");
    }

}
