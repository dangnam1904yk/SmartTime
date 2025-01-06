package com.vinschool.smarttime.controller;

import com.vinschool.smarttime.entity.CategoryClass;
import com.vinschool.smarttime.service.CategoryClassService;
import com.vinschool.smarttime.service.CategoryClassServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dm-lop-hoc")
public class CategoryClassController {
    @Autowired
    private  CategoryClassService service;
    @GetMapping("")
    public  String Search(Model model){
        model.addAttribute("categorys", service.getAll());
        return "page/category/class/home";
    }
    @GetMapping("/save{id}")
    public String CreateOrUpdate(Model model, @RequestParam(name = "id",required = false)  String id, RedirectAttributes rAttributes ){
        CategoryClass categoryClass = new CategoryClass();
        //categoryClass.setActive(true);
        rAttributes.addFlashAttribute("type", "Thêm lớp");
        if(id != null){
            rAttributes.addFlashAttribute("type", "Sửa lớp");
            categoryClass = service.getById(id);
        }
        model.addAttribute("category", categoryClass);
        return "page/category/class/save";
    }

    @PostMapping("/save")
    public String Save(@ModelAttribute("category")  CategoryClass categoryClass, @RequestParam("isActive") boolean isActive ){
        //categoryClass.setActive( isActive);
        service.save(categoryClass);
        return  "redirect:/dm-lop-hoc";
    }

    @PostMapping("/delete/{id}")
    public String Delete(@PathVariable(name = "id") String id){
        service.delete(id);
        return  "redirect:/dm-lop-hoc";
    }
}
