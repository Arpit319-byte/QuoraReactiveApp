package com.example.QuoraReactiveApp.Service;

import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDTO> getQuestionById(String questionId);

    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequest);

    Flux<QuestionResponseDTO> getAllQuestions(String cursor,int page);

    Mono<QuestionResponseDTO> updateQuestion(String questionId, QuestionRequestDTO questionRequest);

    Mono<Void> deleteQuestion(String questionId);

    Flux<QuestionResponseDTO> searchQuestion(String searchTerm, int offset, int pageSize);
}
