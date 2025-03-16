package com.vinschool.smarttime.resfullapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.response.TimeSheetResponsive;
import com.vinschool.smarttime.service.TimeSheetService;
import com.vinschool.smarttime.service.UserService;
import com.vinschool.smarttime.ulti.Constant.ROLE;
import com.vinschool.smarttime.ulti.Constant.ROLE_PREFIX;
import com.vinschool.smarttime.ulti.SecurityUtils;

@RestController
public class RestNoteBookController {
    @Autowired
    private TimeSheetService timeSheetService;
    @Autowired
    private UserService userService;

    @GetMapping("/api/notebook/getByTimeLine")
    public List<TimeSheetResponsive> getListNoteBook(
            @RequestParam(name = "timeLine", required = false) String timLineId,
            @RequestParam(name = "giaovien", required = false) String userId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getCurrentUser();
        List<TimeSheetResponsive> list = timeSheetService.findTimeSheetWithNoteBook();
        if (userPrincipal.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(ROLE_PREFIX.ADMIN))) {
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
        return list;
    }
}
