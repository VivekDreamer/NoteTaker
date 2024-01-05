package com.vivek.NoteTaker.controller;

import com.vivek.NoteTaker.dto.NoteDTO;
import com.vivek.NoteTaker.entity.Note;
import com.vivek.NoteTaker.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    private NoteService noteService;

//    get a list of all notes for the authenticated user.
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(Principal principal) {
        String email = principal.getName();
        System.out.println("email ::::::: "+email);

        List<Note> noteList = noteService.getAllNotes(email);
        System.out.println("noteList :::::: "+noteList);
        return ResponseEntity.ok(noteList);
    }

//    get a note by ID for the authenticated user
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer noteId, Principal principal) {
        String email = principal.getName();
        Note note = noteService.getNoteById(noteId,email);
        return ResponseEntity.ok(note);
    }

//    create a new note for the authenticated user.
    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody NoteDTO noteDTO, Principal principal) {
        String email = principal.getName();
        Note note = noteService.createNote(noteDTO,email);
        return ResponseEntity.ok(note);
    }

//    update an existing note by ID for the authenticated user.
    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Integer noteId, @RequestBody NoteDTO updatedNote, Principal principal) {
        String email = principal.getName();
        Note note = noteService.updateNote(noteId,updatedNote,email);
        if(note == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(note);
    }

//    delete a note by ID for the authenticated user.
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer noteId, Principal principal) {
        String email = principal.getName();
        noteService.delete(noteId,email);
        return ResponseEntity.noContent().build();
    }

//    share a note with another user for the authenticated user.
    @PostMapping("notes/{id}/share")
    public ResponseEntity<String> shareNote(@PathVariable Integer id, @RequestParam String emailToShared, Principal principal) {
        try {
            String userEmail = principal.getName();
            Note sharedNote = noteService.shareNoteWithUser(id, userEmail, emailToShared);
            return new ResponseEntity<>("Note shared successfully with user: " + emailToShared, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

// GET /api/search?q=:query: search for notes based on keywords for the authenticated user.
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam String query, Principal principal) {
        String email = principal.getName();
        List<Note> list = noteService.searchBasedOnKeyword(query,email);
        return ResponseEntity.ok(list);
    }
}
