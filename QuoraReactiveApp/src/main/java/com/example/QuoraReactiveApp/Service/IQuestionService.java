package com.example.QuoraReactiveApp.Service;

import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    Mono<QuestionResponseDTO> getQuestionById(String questionId);

    Mono<QuestionResponseDTO> createQuestion(QuestionResponseDTO questionRequest);

    Flux<QuestionResponseDTO> getAllQuestions(String cursor, int limit);

    Mono<QuestionResponseDTO> updateQuestion(String questionId, QuestionResponseDTO questionRequest);

    Mono<Void> deleteQuestion(String questionId);

    Mono<QuestionResponseDTO> searchQuestion(String searchTerm, int offset, int pageSize);

}
