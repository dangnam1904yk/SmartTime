package com.vinschool.smarttime.resfullapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinschool.smarttime.model.dto.TimeLineDto;
import com.vinschool.smarttime.service.TimeLineService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestTimeLineControlller {
    private final TimeLineService timeLineService;

    @GetMapping("/timeLine/get-by-type")
    public List<TimeLineDto> getMethodName(@RequestParam("type") int param) {
        return timeLineService.findTimeLineByType(param);
    }

}
