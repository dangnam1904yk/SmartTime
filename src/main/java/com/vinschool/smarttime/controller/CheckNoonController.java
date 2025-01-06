package com.vinschool.smarttime.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinschool.smarttime.service.UserService;

@Controller
public class CheckNoonController {

    @Autowired
    private UserService userService;

    @GetMapping("/trong-trua")
    public String CreatDate(Model model) {
        model.addAttribute("users", userService.getAll());
        return "page/checknoon/phan-cong";
    }

    @PostMapping("/lap-lich-trong-trua")
    public String CreateTimeLine(@RequestParam("data") String data)
            throws JsonMappingException, JsonProcessingException {
        System.out.println("Dữ liệu đã chọn: " + data);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> dataList = objectMapper.readValue(data, new TypeReference<>() {
        });
        for (Map<String, Object> item : dataList) {
            System.out.println("ID: " + item.get("date"));
            System.out.println("Name: " + item.get("idUser"));
            System.out.println("Age: " + item.get("codeCa"));
            System.out.println("Age: " + item.get("nameCa"));

            System.out.println("-----------------");
        }
        return "page/checknoon/list";
    }

}
