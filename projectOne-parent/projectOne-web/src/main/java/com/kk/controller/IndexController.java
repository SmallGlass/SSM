package com.kk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yzb on 2018/3/31.
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
