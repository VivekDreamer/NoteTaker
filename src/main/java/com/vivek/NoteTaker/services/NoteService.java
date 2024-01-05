package com.vivek.NoteTaker.services;

import com.vivek.NoteTaker.dto.NoteDTO;
import com.vivek.NoteTaker.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes(String userEmail);

    Note createNote(NoteDTO note,String userEmail);

    Note getNoteById(Integer noteId, String userEmail);

    Note updateNote(Integer noteId, NoteDTO updatedNote, String email);

    void delete(Integer noteId, String email);

    Note shareNoteWithUser(Integer id, String userEmail, String emailToShared);

    List<Note> searchBasedOnKeyword(String query, String email);

}
