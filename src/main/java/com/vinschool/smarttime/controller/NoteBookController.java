package com.vinschool.smarttime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.service.NoteBookService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    @GetMapping("/ky-so-dau-bai")
    public String FormKySo(Model model, HttpServletRequest request,
            @RequestParam(name = "thang-hieu-luc", required = false) String thangHieuLuc) {

        return "";
    }
}
