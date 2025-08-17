package com.example.QuoraReactiveApp.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseModel {

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotBlank(message = "Question ID cannot be blank")
    private String questionId;

}
