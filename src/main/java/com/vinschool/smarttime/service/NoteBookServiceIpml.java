package com.vinschool.smarttime.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.NoteBook;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.NoteBookRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteBookServiceIpml implements NoteBookService {
    private final NoteBookRepository noteBookRepository;

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

}
