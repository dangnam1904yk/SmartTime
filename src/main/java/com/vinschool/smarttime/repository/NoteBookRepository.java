package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.NoteBook;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, String> {

}