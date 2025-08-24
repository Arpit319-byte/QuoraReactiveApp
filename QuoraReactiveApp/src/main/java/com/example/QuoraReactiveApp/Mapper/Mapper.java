package com.example.QuoraReactiveApp.Mapper;

import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Mapper {
    
    private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

    public static Question mapToQuestionEntity(QuestionRequestDTO questionRequestDTO){
        logger.debug("Mapping QuestionRequestDTO to Question entity. Title: '{}', AuthorId: {}", questionRequestDTO.getTitle(), questionRequestDTO.getAuthorId());
        
        Question question = new Question();
        question.setTitle(questionRequestDTO.getTitle());
        question.setContent(questionRequestDTO.getContent());
        question.setAuthorId(questionRequestDTO.getAuthorId());
        
        // Set current timestamp for new questions
        LocalDateTime now = LocalDateTime.now();
        question.setCreatedAt(now);
        question.setUpdatedAt(now);
        
        logger.debug("Successfully mapped QuestionRequestDTO to Question entity. Title: '{}', Content length: {}, CreatedAt: {}", 
                   question.getTitle(), question.getContent().length(), question.getCreatedAt());
        return question;
    }

    public static QuestionResponseDTO mapToQuestionResponseDTO(Question question){
        logger.debug("Mapping Question entity to QuestionResponseDTO. ID: {}, Title: '{}'", question.getId(), question.getTitle());
        
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setId(question.getId());
        questionResponseDTO.setTitle(question.getTitle());
        questionResponseDTO.setContent(question.getContent());
        questionResponseDTO.setAuthorId(question.getAuthorId());
        
        // Handle null dates gracefully
        LocalDateTime createdAt = question.getCreatedAt();
        LocalDateTime updatedAt = question.getUpdatedAt();
        
        if (createdAt == null) {
            logger.warn("Question with ID {} has null createdAt, setting to current time", question.getId());
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            logger.warn("Question with ID {} has null updatedAt, setting to current time", question.getId());
            updatedAt = LocalDateTime.now();
        }
        
        questionResponseDTO.setCreatedAt(createdAt);
        questionResponseDTO.setUpdatedAt(updatedAt);
        
        logger.debug("Successfully mapped Question entity to QuestionResponseDTO. ID: {}, Title: '{}', Content length: {}, CreatedAt: {}, UpdatedAt: {}", 
                   questionResponseDTO.getId(), questionResponseDTO.getTitle(), questionResponseDTO.getContent().length(), 
                   questionResponseDTO.getCreatedAt(), questionResponseDTO.getUpdatedAt());
        return questionResponseDTO;
    }
}
