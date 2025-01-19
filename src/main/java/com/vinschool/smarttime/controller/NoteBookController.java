package com.vinschool.smarttime.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.request.NoteBookStatusRequest;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.NoteBookService;
import com.vinschool.smarttime.service.TimeLineService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    @Autowired
    private TimeLineService timeLineService;
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    private UserService userService;

    @GetMapping("/ky-so-dau-bai")
    public String FormKySo(Model model, HttpServletRequest request,
            @RequestParam(name = "thang-hieu-luc", required = false) String thangHieuLuc) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        model.addAttribute("timeLine",
                timeLineService.findByEndDateAfterAndType(LocalDate.now(), Constant.TYPE_TIME_LINE.SO_DAU_BAI));
        return "page/note-book/form";
    }

    @PostMapping("/ky-so-dau-bai")
    public String KySoDauBai(Model model, HttpServletRequest request,
            @RequestParam(name = "data") String listData) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";
        noteBookService.saveNoteBookFromStringData(listData, request);
        return "page/note-book/form";
    }

    @GetMapping("/danh-sach")
    public String DanhSach(Model model, HttpServletRequest request,
            @RequestParam(name = "timeLine", required = false) String timLineId,
            @RequestParam(name = "giaovien", required = false) String userId) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/dang-nhap";

        model.addAttribute("timeLine", timeLineService.findByType(Constant.TYPE_TIME_LINE.SO_DAU_BAI));
        model.addAttribute("users", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));

        List<TimeSheetResponsive> list = new ArrayList<>();
        if (user.getEmail().startsWith("admin")) {
            if (timLineId == null || timLineId.isEmpty()) {
                list = timeSheetService.findTimeSheetWithNoteBook();

            } else {
                list = timeSheetService.findTimeSheetByIdTimeLineWithNoteBook(timLineId);
            }
        } else {
            if (timLineId == null || timLineId.isEmpty()) {
                list = timeSheetService.findTimeSheetByUserIdTeachWithNoteBook(user.getId());
            } else {
                list = timeSheetService.findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(timLineId, user.getId());
            }
        }

        for (TimeSheetResponsive timeSheetResponsive : list) {

            if (timeSheetResponsive.getTimeLineEndate() != null
                    && timeSheetResponsive.getTimeLineEndate().isBefore(LocalDate.now())) {
                timeSheetResponsive.setNoAction(true);
            }
        }
        model.addAttribute("list", list);
        return "page/note-book/list";
    }

    @PostMapping("/cap-nhat-trang-thai")
    public String UpdateStatusNotBook(Model model, HttpServletRequest request,
            @RequestParam("listData") String listData) {
        noteBookService.updateStatusNoteBook(listData, request);
        return "redirect:/danh-sach";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable(name = "id") String param, HttpServletRequest request) {
        noteBookService.deleteById(param, request);
        return "redirect:/danh-sach";
    }

    @GetMapping("/ky-so-dau-bai/edit/{id}")
    public String getForm(@PathVariable(name = "id") String param, HttpServletRequest request, Model model) {
        model.addAttribute("noteBook", timeSheetService.findNoteBookByIdWWithTimeSheet(param));
        return "page/note-book/edit";
    }

    @PostMapping("/ky-so-dau-bai/edit")
    public String updateNoteBook(@ModelAttribute("noteBook") TimeSheetResponsive param, HttpServletRequest request,
            Model model) {
        System.out.println(param);
        return "page/note-book/edit";
    }

}
