package com.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dongly on 2019/7/23
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "redirect:login.jsp";
    }
}
