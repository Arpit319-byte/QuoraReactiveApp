package com.example.QuoraReactiveApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDTO{

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 20, max = 1000, message = "Content must be between 20 and 1000 characters")
    private String content;

    @NotBlank(message = "Question ID cannot be blank")
    private String questionId;


}