package com.vinschool.smarttime.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinschool.smarttime.entity.CheckNoon;
import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.mapper.CheckNoonMapper;
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.model.request.CheckNoonRequest;
import com.vinschool.smarttime.model.response.CheckNoonResponsive;
import com.vinschool.smarttime.repository.CheckNoonRepository;
import com.vinschool.smarttime.ulti.Constant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckNoonServiceImpl implements CheckNoonService {
    private final CheckNoonRepository noonService;

    private final CheckNoonMapper checkNoonMapper;
    private final TimeLineService timeLineService;

    @Override
    public void save(CheckNoon checkNoon) {
        noonService.save(checkNoon);
    }

    @Override
    public void delete(String id) {
        noonService.deleteById(id);
    }

    @Override
    public List<CheckNoon> getAll() {
        return noonService.findAll();
    }

    @Override
    public void LuuPhanCongLich(String request, String timeLine, UserPrincipal user) {
        ObjectMapper objectMapper = new ObjectMapper();
        TimeLine timeLineDb = new TimeLine();
        List<Map<String, Object>> dataList;
        try {
            Map<String, Object> map = objectMapper.readValue(timeLine, new TypeReference<>() {
            });
            ;

            timeLineDb.setActive(
                    map.get("isActive") != null ? map.get("isActive").toString().equals("true") ? true : false : false);
            timeLineDb.setMonth(map.get("month").toString());
            timeLineDb.setType(Constant.TYPE_TIME_LINE.TRONG_TRUA);
            TimeLine timeLineSave = timeLineService.save(timeLineDb);

            dataList = objectMapper.readValue(request, new TypeReference<>() {
            });
            List<CheckNoonRequest> list = new ArrayList<>();
            for (Map<String, Object> item : dataList) {

                CheckNoonRequest noonRequest = new CheckNoonRequest();
                noonRequest.setId(item.get("id") != null ? item.get("id").toString() : null);
                noonRequest.setDateWork(LocalDate.parse(item.get("date").toString()));
                noonRequest.setIdUser(item.get("idUser").toString());
                noonRequest.setCreateDate(new Date());
                noonRequest.setCreateBy(user.getUser().getId());
                noonRequest.setMaCa(item.get("codeCa").toString());
                noonRequest.setNameCa(item.get("nameCa").toString());
                noonRequest.setTimeLine(timeLineSave);
                list.add(noonRequest);
            }
            timeLineDb.setCheckNoon(checkNoonMapper.toListCheckNoone(list));
            timeLineDb.setId(timeLineSave.getId());
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        timeLineService.save(timeLineDb);
    }

    @Override
    public List<CheckNoon> findByUserIdWorkAndTimeLine(String uesrid, String month) {
        return noonService.findByUserIdWorkAndTimeLine(uesrid, month);
    }

    @Override
    public List<CheckNoon> findByUserIdCreateAndTimeLine(String uesrid, String month) {
        return noonService.findByUserIdCreateAndTimeLine(uesrid, month);
    }

    @Override
    public List<CheckNoonResponsive> findByUserIdWorkAndTimeLineDto(String uesrid, String month) {
        return noonService.findByUserIdWorkAndTimeLineDto(uesrid, month);
    }

    @Override
    public List<CheckNoonResponsive> findByUserIdCreateAndTimeLineDto(String userid, String month) {
        return noonService.findByUserIdCreateAndTimeLineDto(userid, month);
    }

    // @Override
    // public List<CheckNoonResponsive>
    // findByUserIdCreateAndTimeLineDtoNameJDPC(String userid, String month) {
    // return noonService2.findByUserIdCreateAndTimeLineDtoNameJDPC(userid, month);
    // }

    @Override
    public void ChamCong(String data, UserPrincipal user) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> dataList;
        try {
            dataList = objectMapper.readValue(data, new TypeReference<>() {
            });
            List<CheckNoonRequest> list = new ArrayList<>();
            List<CheckNoon> listCheckNoonDb = new ArrayList<>();

            for (Map<String, Object> item : dataList) {
                CheckNoon checkNoon = new CheckNoon();
                if (item.get("id") != null) {
                    Optional<CheckNoon> optionnChecNoon = noonService.findById(item.get("id").toString());
                    if (optionnChecNoon.isPresent()) {
                        checkNoon = optionnChecNoon.get();
                    }
                }
                if (item.get("isCheck").toString().equals("true")) {
                    checkNoon.setCheck(true);
                } else {
                    checkNoon.setCheck(false);
                }

                listCheckNoonDb.add(checkNoon);
            }
            noonService.saveAll(listCheckNoonDb);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<CheckNoon> findByUserIdWork(String uesrid) {
        return noonService.findByUserIdWork(uesrid);
    }

    @Override
    public List<CheckNoon> findByUserIdCreate(String userid) {
        return noonService.findByUserIdCreate(userid);
    }
}
