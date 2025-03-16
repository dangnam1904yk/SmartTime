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
import com.vinschool.smarttime.model.dto.UserPrincipal;
import com.vinschool.smarttime.repository.NoteBookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteBookServiceIpml implements NoteBookService {
    private final NoteBookRepository noteBookRepository;
    private final TimeSheetService timeSheetService;

    @Override
    public NoteBook save(NoteBook noteBook) {
        return noteBookRepository.save(noteBook);
    }

    @Override
    public void deleteById(String id) {
        noteBookRepository.deleteById(id);
    }

    @Override
    public NoteBook findById(String id) {
        Optional<NoteBook> optional = noteBookRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public List<NoteBook> findAll() {
        return noteBookRepository.findAll();
    }

    @Override
    public void saveNoteBookFromStringData(String data, UserPrincipal user) {
        ObjectMapper objectMapper = new ObjectMapper();
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
                noteBook.setCreateBy(user.getUser().getId());
                noteBook.setCreateDate(new Date());
                noteBook.setTimeSheet(timeSheetService.detail(item.get("timeSheetId").toString()));
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
    public void updateStatusNoteBook(String data, UserPrincipal user) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> dataList;
        try {
            dataList = objectMapper.readValue(data, new TypeReference<>() {
            });
            List<NoteBook> list = new ArrayList<>();
            for (Map<String, Object> item : dataList) {

                NoteBook noteBook = null;
                if (item.get("id") != null) {
                    noteBook = findById(item.get("id").toString());
                }
                // if (item.get("isTrain").toString().equals("true")) {
                // noteBook.setTrain(true);
                // } else {
                // noteBook.setTrain(false);
                // }
                noteBook.setIsTrain(Boolean.valueOf(item.get("isTrain").toString()));
                noteBook.setUpdateBy(user.getUser().getId());
                noteBook.setUpdateDate(new Date());
                list.add(noteBook);
                save(noteBook);
            }

        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
    }

}
