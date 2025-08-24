package com.example.QuoraReactiveApp.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "answers")
public class Answer extends BaseModel {

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotBlank(message = "Question ID cannot be blank")
    private String questionId;
}
