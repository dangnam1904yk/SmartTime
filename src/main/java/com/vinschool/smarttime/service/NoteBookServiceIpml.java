package com.vinschool.smarttime.service;

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
import com.vinschool.smarttime.entity.NoteBook;
import com.vinschool.smarttime.entity.TimeLine;
import com.vinschool.smarttime.entity.TimeSheet;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.NoteBookRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteBookServiceIpml implements NoteBookService {
    private final NoteBookRepository noteBookRepository;
    private final TimeSheetService timeSheetService;

    @Override
    public NoteBook save(NoteBook noteBook, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return null;
        return noteBookRepository.save(noteBook);
    }

    @Override
    public void deleteById(String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null)
            noteBookRepository.deleteById(id);
    }

    @Override
    public NoteBook findById(String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Optional<NoteBook> optional = noteBookRepository.findById(id);
            if (optional.isPresent())
                return optional.get();
        }
        return null;
    }

    @Override
    public List<NoteBook> findAll() {
        return noteBookRepository.findAll();
    }

    @Override
    public void saveNoteBookFromStringData(String data, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return;
        ObjectMapper objectMapper = new ObjectMapper();
        TimeLine timeLineDb = new TimeLine();
        List<Map<String, Object>> dataList;
        try {
            dataList = objectMapper.readValue(data, new TypeReference<>() {
            });
            List<NoteBook> list = new ArrayList<>();
            for (Map<String, Object> item : dataList) {

                NoteBook noteBook = new NoteBook();
                if (item.get("id") == null) {
                    noteBook.setId(null);
                } else {
                    noteBook.setId(item.get("id").toString());
                }
                noteBook.setCreateBy(user.getId());
                noteBook.setCreateDate(new Date());
                noteBook.setTimeSheet(timeSheetService.detail(item.get("timeSheetId").toString(), request));
                noteBook.setDetailErrorStudent(item.get("detailErrorStudent").toString());
                noteBook.setGeneralComment(item.get("generalComment").toString());
                noteBook.setLessonTopic(item.get("lessonTopic").toString());
                noteBook.setStudentErrorCount(Integer.valueOf(item.get("studentErrorCount").toString()));
                noteBook.setLessonEvaluation(Double.parseDouble(item.get("lessonEvaluation").toString()));
                noteBook.setIsTrain(Boolean.valueOf(item.get("isTrain").toString()));
                // if (item.get("isTrain").toString() == "true") {
                // noteBook.setTrain(true);
                // } else {
                // noteBook.setTrain(false);
                // }
                list.add(noteBook);
            }

            noteBookRepository.saveAll(list);

        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void saveAll(List<NoteBook> list) {
        noteBookRepository.saveAll(list);
    }

    @Override
    public void updateStatusNoteBook(String data, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> dataList;
        try {
            dataList = objectMapper.readValue(data, new TypeReference<>() {
            });
            List<NoteBook> list = new ArrayList<>();
            for (Map<String, Object> item : dataList) {

                NoteBook noteBook = null;
                if (item.get("id") != null) {
                    noteBook = findById(item.get("id").toString(), request);
                }
                // if (item.get("isTrain").toString().equals("true")) {
                // noteBook.setTrain(true);
                // } else {
                // noteBook.setTrain(false);
                // }
                noteBook.setIsTrain(Boolean.valueOf(item.get("isTrain").toString()));
                noteBook.setUpdateBy(user.getId());
                noteBook.setUpdateDate(new Date());
                list.add(noteBook);
                save(noteBook, request);
            }
            // noteBookRepository.saveAll(list);

        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
    }

}
