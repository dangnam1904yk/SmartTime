package com.vinschool.smarttime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckNoonController {

    @GetMapping("/trong-trua")
    public String CreatDate(Model model){
        return  "page/checknoon/phan-cong";
    }

}
