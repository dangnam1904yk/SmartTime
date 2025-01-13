package com.vinschool.smarttime.resfullapi;

import org.springframework.web.bind.annotation.RestController;

import com.vinschool.smarttime.service.TimeLineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestTimeLineControlller {
    private final TimeLineService timeLineService;

}
