package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.NoteBook;

import jakarta.servlet.http.HttpServletRequest;

public interface NoteBookService {
    NoteBook save(NoteBook noteBook, HttpServletRequest request);

    void deleteById(String id, HttpServletRequest request);

    NoteBook findById(String id, HttpServletRequest request);

    List<NoteBook> findAll();
}
