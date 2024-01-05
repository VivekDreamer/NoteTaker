package com.vivek.NoteTaker.dto;

import com.vivek.NoteTaker.entity.Note;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "password should not be blank")
    private String password;
    @Email(message = "email should be valid")
    private String email;
    private List<Note> notes = new ArrayList<>();
}
