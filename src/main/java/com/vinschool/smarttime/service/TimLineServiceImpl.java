package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.model.dto.TimeLineDto;
import com.vinschool.smarttime.repository.TimeLineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimLineServiceImpl implements TimeLineService {

    private final TimeLineRepository lineRepository;

    @Override
    public TimeLine save(TimeLine timeLine) {
        return lineRepository.save(timeLine);
    }

    @Override
    public void detele(String id) {
        lineRepository.deleteById(id);
    }

    @Override
    public TimeLine Detail(String id) {
        return lineRepository.findById(id).get();
    }

    @Override
    public List<TimeLine> getAll() {
        return lineRepository.findAll();
    }

    @Override
    public List<TimeLine> findByType(int type) {
        return lineRepository.findByType(type);
    }

    @Override
    public List<TimeLineDto> findTimeLineByType(int type) {
        return lineRepository.findTimeLineByType(type);
    }

    @Override
    public List<TimeLine> findByEndDateAfterAndType(LocalDate date, int type) {
        return lineRepository.findByEndDateAfterAndType(date, type);
    }

}
