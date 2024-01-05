package com.vivek.NoteTaker.services.impl;

import com.vivek.NoteTaker.dto.NoteDTO;
import com.vivek.NoteTaker.entity.Note;
import com.vivek.NoteTaker.entity.User;
import com.vivek.NoteTaker.exceptions.NotAuthorizedUserException;
import com.vivek.NoteTaker.exceptions.NoteNotFoundException;
import com.vivek.NoteTaker.exceptions.UserNotFoundException;
import com.vivek.NoteTaker.repository.NoteRepository;
import com.vivek.NoteTaker.repository.UserRepository;
import com.vivek.NoteTaker.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Note> getAllNotes(String userEmail) {
        User user = userRepository.findByEmail(userEmail).get();
        List<Note> noteList = user.getNotes();
        return noteList;
    }

   @Override
   public Note createNote(NoteDTO noteDTO,String userEmail) {
       User user = userRepository.findByEmail(userEmail).get();
       Note note = new Note();
       note.setContent(noteDTO.getContent());
       note.setTitle(noteDTO.getTitle());
       note.setUser(Arrays.asList(user));
       note.setDateAdded(new Date());
       Note returnedNote = noteRepository.save(note);
       return returnedNote;
   }

    @Override
    public Note getNoteById(Integer noteId, String email) {
        Optional<Note> optional = noteRepository.findById(noteId);
        if(optional.isPresent()){
            Note note = optional.get();
            if (isNoteAssociatedWithUser(note, email)) {
                return note;
            } else {
                throw new NotAuthorizedUserException("note Id : "+noteId +" is not belongs to the authenticated user.");
            }
        }else {
            throw new NoteNotFoundException("Note not found");
        }
    }

    private boolean isNoteAssociatedWithUser(Note note, String email) {
        return note.getUser().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public Note updateNote(Integer noteId, NoteDTO updatedNote, String email) {
        Optional<Note> optional = noteRepository.findById(noteId);
        if(optional.isPresent()){
            Note note = optional.get();
            if (isNoteAssociatedWithUser(note, email)) {
                note.setDateAdded(new Date());
                note.setTitle(updatedNote.getTitle());
                note.setContent(updatedNote.getContent());
                noteRepository.save(note);
                return note;
            } else {
                throw new NotAuthorizedUserException("note Id : "+noteId +" is not belongs to the authenticated user.");
            }
        }else {
            throw new NoteNotFoundException("Note not found");
        }
    }

    @Override
    public void delete(Integer noteId, String email) {
        // Check if the note exists
        Optional<Note> optional = noteRepository.findById(noteId);
        if(optional.isPresent()){
            Note note = optional.get();
            if (isNoteAssociatedWithUser(note, email)) {
                noteRepository.deleteById(noteId);
            } else {
                throw new NotAuthorizedUserException("note Id : "+noteId +" is not belongs to the authenticated user.");
            }
        }else {
            throw new NoteNotFoundException("Note not found");
        }
    }

    @Override
    public Note shareNoteWithUser(Integer id, String userEmail, String emailToShared) {
        Optional<Note> optional = noteRepository.findById(id);
        if(optional.isPresent()){
            Note note = optional.get();
            if (isNoteAssociatedWithUser(note, userEmail)) {
                Optional<User> userOptional = userRepository.findByEmail(emailToShared);
                if(userOptional.isPresent()) {
                    List<User> userList = note.getUser();
                    userList.add(userOptional.get());
                    return noteRepository.save(note);
                }
                throw new UserNotFoundException("shared email user does not exist!!");
            } else {
                throw new NotAuthorizedUserException("note Id : "+id +" is not belongs to the authenticated user.");
            }
        }else {
            throw new NoteNotFoundException("Note not found");
        }
    }

    @Override
    public List<Note> searchBasedOnKeyword(String query, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return noteRepository.findByUser_IdAndTitleContainingOrUser_IdAndContentContaining(user.getId(), query, user.getId(), query);
        } else{
            throw new UserNotFoundException("user not found");
        }
    }
}
