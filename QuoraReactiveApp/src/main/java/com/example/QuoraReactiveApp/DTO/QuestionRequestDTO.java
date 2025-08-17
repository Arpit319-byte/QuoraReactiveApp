package com.example.QuoraReactiveApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {


    @NotBlank(message = "Title cannot be blank")
    @Size(min=10, max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(min=20, max = 1000, message = "Content must be between 20 and 1000 characters")
    private String content;

}
