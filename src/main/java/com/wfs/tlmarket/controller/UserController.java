package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.constants.Constants;
import com.wfs.tlmarket.models.GoodsInfo;
import com.wfs.tlmarket.service.GoodsService;
import com.wfs.tlmarket.service.response.Response;
import com.wfs.tlmarket.models.UserInfo;

import com.wfs.tlmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 创建人：王福顺  创建时间：2019/10/22
 */
@RestController
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/")
    public ModelAndView index(Model model, HttpServletResponse httpResponse) {
        // 检查cookie中 有无账户信息
        UserInfo userInfo = checkCookieUserName();
        if (null != userInfo) {
            System.out.println("有账号信息");
            auth(userInfo.getUserName(), userInfo.getPassword(), httpResponse, model);
        }
        // 首页信息
        Response<List<GoodsInfo>> indexResponse = goodsService.selectGoodsInfoList(0);
        session.setAttribute(Constants.SESSION_GOODS_LIST, indexResponse);
        System.out.println("session中的userInfo" + session.getAttribute("userInfo"));
       return new ModelAndView("index");
    }

    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("login");
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletResponse httpResponse) {
        session.removeAttribute(Constants.SESSION_USER_INFO);
        removeCookie(httpResponse);
        return new ModelAndView("index");
    }

    /**
     * 删除cookie中 用户信息
     */
    private void removeCookie(HttpServletResponse httpServletResponse) {
        Cookie[] cookies = request.getCookies();
        String tlUserNameKey;
        String tlPasswordKey;
        for (Cookie cookie : cookies) {
            tlUserNameKey = cookie.getName();
            tlPasswordKey = cookie.getName();
            if (tlUserNameKey.equals(Constants.COOKIE_USER_NAME)  ||  tlPasswordKey.equals(Constants.COOKIE_PASSWORD)) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                httpServletResponse.addCookie(cookie);
            }
        }
    }

    /**
     * 登录鉴权
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/auth")
    public ModelAndView auth (String userName, String password, HttpServletResponse httpResponse, Model model) {
        // 构建参数
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        Response<UserInfo> response = userService.auth(userInfo);
        // 本次的鉴权信息
        model.addAttribute(Constants.MODEL_RESPONSE, response);
        if (response.getIsSuccess()) {
            // 成功 存 cookie
            setCookie(httpResponse, userName, password);
            session.setAttribute(Constants.SESSION_USER_INFO, response.getResult());
        }else {
            // 失败
            return new ModelAndView("login");
        }
        // 成功 进入首页
        return new ModelAndView("redirect:/");
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(String userName, String password, Model model) {
        if (null == userName || null == password) {
            // 进入注册通道
            return new ModelAndView("register");
        }
        // 构建参数
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        // 注册业务
        Response response = userService.register(userInfo);
        // 传递结果
        model.addAttribute(Constants.MODEL_RESPONSE, response);
        // 失败跳转
        if (!response.getIsSuccess()) {
            return new ModelAndView("register");
        }
        return new ModelAndView("login");
    }


    /**
     * 检查cookie中有无账户信息
     * @return
     */
    private UserInfo checkCookieUserName() {
        UserInfo userInfo = new UserInfo();
        Cookie[] cookies = request.getCookies();
        int count = 0;
        if (cookies != null  &&  cookies.length != 0){
            String tlUserNameKey;
            String tlPasswordKey;
            for (Cookie cookie : cookies) {
                tlUserNameKey = cookie.getName();
                tlPasswordKey = cookie.getName();
                if (tlUserNameKey.equals(Constants.COOKIE_USER_NAME)) {
                    count++;
                    userInfo.setUserName(cookie.getValue());
                }
                if (tlPasswordKey.equals(Constants.COOKIE_PASSWORD)) {
                    count++;
                    userInfo.setPassword(cookie.getValue());
                }
            }
        }
        // cookie中 存有账户密码
        if (count == 2) {
            return userInfo;
        }
        return null;
    }

    /**
     * 设置cookie
     * @param response
     */
    private void setCookie(HttpServletResponse response, String userName, String password) {
        Cookie userNameCookie = new Cookie(Constants.COOKIE_USER_NAME, userName);
        Cookie passwordCookie = new Cookie(Constants.COOKIE_PASSWORD, password);
        userNameCookie.setMaxAge(Constants.SEVEN_DAY_SEC);
        passwordCookie.setMaxAge(Constants.SEVEN_DAY_SEC);
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
    }


}
