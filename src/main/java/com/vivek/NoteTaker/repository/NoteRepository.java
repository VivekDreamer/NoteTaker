package com.vivek.NoteTaker.repository;

import com.vivek.NoteTaker.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
      List<Note> findByUser_IdAndTitleContainingOrUser_IdAndContentContaining(
            Integer userId, String title, Integer userIdAgain, String content
      );
}
