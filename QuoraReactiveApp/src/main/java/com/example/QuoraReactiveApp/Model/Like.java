package com.example.QuoraReactiveApp.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "likes")
public class Like extends BaseModel {

    @NotBlank(message="Target ID cannot be blank")
    private String targetId;

    @NotBlank(message="Target type cannot be blank")
    private String targetType; // e.g., "answer", "question"
}
