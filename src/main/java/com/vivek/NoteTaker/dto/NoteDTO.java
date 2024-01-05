package com.vivek.NoteTaker.dto;

import com.vivek.NoteTaker.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private String title;
    private String content;
}
