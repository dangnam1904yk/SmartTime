package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.NoteBook;
import com.vinschool.smarttime.model.dto.UserPrincipal;

public interface NoteBookService {
    NoteBook save(NoteBook noteBook);

    void deleteById(String id);

    NoteBook findById(String id);

    List<NoteBook> findAll();

    void saveAll(List<NoteBook> list);

    void saveNoteBookFromStringData(String data, UserPrincipal user);

    void updateStatusNoteBook(String data, UserPrincipal user);
}
