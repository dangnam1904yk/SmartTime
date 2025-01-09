package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vinschool.smarttime.service.CategoryPeriodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryPeriodControlller {
    @Autowired
    private CategoryPeriodService categoryPeriodService;

    @GetMapping("/dm-tiet-hoc")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
