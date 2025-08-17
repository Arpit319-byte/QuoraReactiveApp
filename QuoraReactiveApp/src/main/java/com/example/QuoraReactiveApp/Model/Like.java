package com.example.QuoraReactiveApp.Model;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like  extends BaseModel{

    @NotBlank(message="Target ID cannot be blank")
    private String targetId;

    @NotBlank(message="Target type cannot be blank")
    private String targetType; // e.g., "answer", "question"
}
