package com.wfs.tlmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人：王福顺  创建时间：2019/10/22
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, Map map, Model model) {
        System.out.println(username);
        map.put("message","123");
        model.addAttribute("message2","444");

        return "{\"errormessage\",\"999\"}";
    }

}
