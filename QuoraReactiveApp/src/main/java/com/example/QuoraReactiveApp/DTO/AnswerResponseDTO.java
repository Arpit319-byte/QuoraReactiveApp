package com.example.QuoraReactiveApp.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO{

    public String id;
    public String content;
    public String questionId;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}