package com.dong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class MenuController {

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }
}
