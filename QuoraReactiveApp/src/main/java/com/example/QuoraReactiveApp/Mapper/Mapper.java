package com.example.QuoraReactiveApp.Mapper;


import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Model.Question;

public class Mapper {

    public static Question mapToQuestionEntity(QuestionRequestDTO questionRequestDTO){
        Question question=new Question();
        question.setTitle(questionRequestDTO.getTitle());
        question.setContent(questionRequestDTO.getContent());
        question.setAuthorId(questionRequestDTO.getAuthorId());
        return question;
    }

    public static QuestionResponseDTO mapToQuestionResponseDTO(Question question){
        QuestionResponseDTO questionResponseDTO=new QuestionResponseDTO();
        questionResponseDTO.setId(question.getId());
        questionResponseDTO.setTitle(question.getTitle());
        questionResponseDTO.setContent(question.getContent());
        questionResponseDTO.setAuthorId(question.getAuthorId());
        questionResponseDTO.setCreatedAt(question.getCreatedAt());
        return questionResponseDTO;
    }
}
