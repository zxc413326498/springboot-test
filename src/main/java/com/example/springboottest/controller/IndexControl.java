package com.example.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControl {

    @RequestMapping("/")
    private String index(){
        return "redirect:/design";
    }
}