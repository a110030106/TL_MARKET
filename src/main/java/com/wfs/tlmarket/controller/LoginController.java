package com.wfs.tlmarket.controller;

import com.wfs.tlmarket.models.Response;
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

/**
 * 创建人：王福顺  创建时间：2019/10/22
 */
@RestController
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(Model model, HttpServletResponse response) {
        // 设置cookie(测试)
//        setCookie(response);

        // 检查cookie中 有无账户信息
        UserInfo userInfo = checkCookieUserName();
        model.addAttribute("userInfo",userInfo);

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
        Response response = userService.auth(userInfo);
        model.addAttribute("response",response);
        if (response.getIsSuccess()) {
            // 成功 存 cookie
            setCookie(httpResponse, userName, password);
        }else {
            // 失败
            return new ModelAndView("login");
        }
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
        model.addAttribute("response",response);
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
                if (tlUserNameKey.equals("tlUserName")) {
                    count++;
                    userInfo.setUserName(cookie.getValue());
                }
                if (tlPasswordKey.equals("tlPassword")) {
                    count++;
                    userInfo.setPassword(cookie.getValue());
                }
            }
        }
        // cookie中 存有账户密码
        if (count == 2) {
            System.out.println("有用户 cookie");
            return userInfo;
        }
        return null;
    }

    /**
     * 设置cookie 测试用
     * @param response
     */
    private void setCookie(HttpServletResponse response, String userName, String password) {
        Cookie userNameCookie = new Cookie("tlUserName",userName);
        Cookie passwordCookie = new Cookie("tlPassword",password);
        userNameCookie.setMaxAge(3600 * 24 *7);
        passwordCookie.setMaxAge(3600 * 24 *7);
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
    }


}
