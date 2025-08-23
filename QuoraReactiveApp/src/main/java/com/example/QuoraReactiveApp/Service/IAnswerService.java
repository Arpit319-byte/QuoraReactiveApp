package com.example.QuoraReactiveApp.Service;

import com.example.QuoraReactiveApp.DTO.AnswerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {

    Mono<AnswerResponseDTO> getAnswerById(String answerId);
    Mono<AnswerResponseDTO> createAnswer(AnswerResponseDTO answerRequest);
    Mono<AnswerResponseDTO> updateAnswer(String answerId, AnswerResponseDTO answerRequest);
    Mono<Void> deleteAnswer(String answerId);
    Mono<AnswerResponseDTO> searchAnswer(String searchTerm, int offset, int pageSize);
    Flux<AnswerResponseDTO> getAnswerByQuestionId(String questionId, String cursor, int limit);

}
