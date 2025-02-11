package com.vinschool.smarttime.resfullapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.TimeLineService;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class RestNoteBookController {
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    private UserService userService;

    @GetMapping("/api/notebook/getByTimeLine")
    public List<TimeSheetResponsive> getListNoteBoo(
            @RequestParam(name = "timeLine", required = false) String timLineId,
            @RequestParam(name = "giaovien", required = false) String userId,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return null;

        List<TimeSheetResponsive> list = timeSheetService.findTimeSheetWithNoteBook();
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
                list = timeSheetService.findTimeSheetByIdTimeLineAndUserIdTeachWithNoteBook(timLineId,
                        user.getId());
            }
        }
        return list;
    }
}
