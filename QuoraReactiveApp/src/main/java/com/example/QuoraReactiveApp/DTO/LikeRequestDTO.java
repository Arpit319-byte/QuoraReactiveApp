package com.example.QuoraReactiveApp.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDTO {

    @NotBlank(message = "User ID cannot be blank")
    private String targetId; // ID of the answer or question being liked

    @NotBlank(message = "Target type cannot be blank")
    private String targetType; // Type of the target, e.g., "answer" or "question"

    private  Boolean isLike; // true for like, false for dislike

}
