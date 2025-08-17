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
public class LikeResponseDTO {

    private String id;
    private String targetId; // ID of the question or answer being liked
    private String targetType; // Type of the target, e.g., "answer" or "question"
    private Boolean isLike; // true for like, false for dislike
    private LocalDateTime createdAt; // Timestamp of when the like was created
}
