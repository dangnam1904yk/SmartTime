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
import org.springframework.web.bind.annotation.RequestParam;

import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.NoteBookService;
import com.vinschool.smarttime.service.TimeLineService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;
import com.vinschool.smarttime.ulti.SecurityUtils;
import com.vinschool.smarttime.ulti.Constant.ROLE;
import com.vinschool.smarttime.ulti.Constant.ROLE_PREFIX;

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
    public String FormKySo(Model model,
            @RequestParam(name = "thang-hieu-luc", required = false) String thangHieuLuc) {
        model.addAttribute("timeLine",
                // timeLineService.findByEndDateAfterAndType(LocalDate.now(),
                // Constant.TYPE_TIME_LINE.SO_DAU_BAI));
                timeLineService.findByType(Constant.TYPE_TIME_LINE.SO_DAU_BAI));
        return "page/note-book/form";
    }

    @PostMapping("/ky-so-dau-bai")
    public String KySoDauBai(Model model,
            @RequestParam(name = "data") String listData) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        noteBookService.saveNoteBookFromStringData(listData, userPrincipal);
        return "page/note-book/form";
    }

    @GetMapping("/danh-sach")
    public String DanhSach(Model model,
            @RequestParam(name = "timeLine", required = false) String timLineId,
            @RequestParam(name = "giaovien", required = false) String userId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        model.addAttribute("timeLine", timeLineService.findByType(Constant.TYPE_TIME_LINE.SO_DAU_BAI));
        model.addAttribute("users", userService.findUserByCodeRole(Constant.ROLE.GIAO_VIEN));

        List<TimeSheetResponsive> list = new ArrayList<>();
        if (userPrincipal.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(Constant.ROLE_PREFIX.ADMIN))) {
            if (timLineId == null || timLineId.isEmpty()) {
                list = timeSheetService.findTimeSheetWithNoteBook();

            } else {
                list = timeSheetService.findTimeSheetByIdTimeLineWithNoteBook(timLineId);
            }
        } else {
            if (timLineId == null || timLineId.isEmpty()) {
                list = timeSheetService.findTimeSheetByUserIdTeachWithNoteBook(userPrincipal.getUser().getId());
            } else {
                list = timeSheetService.findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(timLineId,
                        userPrincipal.getUser().getId());
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
    public String UpdateStatusNotBook(Model model, @RequestParam("listData") String listData) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        noteBookService.updateStatusNoteBook(listData, userPrincipal);
        return "redirect:/danh-sach";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable(name = "id") String param) {
        noteBookService.deleteById(param);
        return "redirect:/danh-sach";
    }

    @GetMapping("/ky-so-dau-bai/edit/{id}")
    public String getForm(@PathVariable(name = "id") String param, Model model) {
        model.addAttribute("noteBook", timeSheetService.findNoteBookByIdWWithTimeSheet(param));
        return "page/note-book/edit";
    }

    @PostMapping("/ky-so-dau-bai/edit")
    public String updateNoteBook(@ModelAttribute("noteBook") TimeSheetResponsive param, Model model) {
        return "page/note-book/edit";
    }

}
