package com.vinschool.smarttime.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.CategorySubject;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.CategorySubjectRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorySubjectServiceImpl implements CategorySubjectService {

    private final CategorySubjectRepository repository;

    @Override
    public CategorySubject save(CategorySubject subject, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return null;
        CategorySubject category = repository.save(subject);
        return category;
    }

    @Override
    public CategorySubject detail(String id) {
        Optional<CategorySubject> option = repository.findById(id);
        if (option.isPresent())
            return option.get();
        return null;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<CategorySubject> getAll() {
        return repository.findAll();
    }

}
