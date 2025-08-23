package com.example.QuoraReactiveApp.Service;


import com.example.QuoraReactiveApp.DTO.AnswerResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnswerService implements IAnswerService {
    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String answerId) {
        return null;
    }

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerResponseDTO answerRequest) {
        return null;
    }

    @Override
    public Mono<AnswerResponseDTO> updateAnswer(String answerId, AnswerResponseDTO answerRequest) {
        return null;
    }

    @Override
    public Mono<Void> deleteAnswer(String answerId) {
        return null;
    }

    @Override
    public Mono<AnswerResponseDTO> searchAnswer(String searchTerm, int offset, int pageSize) {
        return null;
    }

    @Override
    public Flux<AnswerResponseDTO> getAnswerByQuestionId(String questionId, String cursor, int limit) {
        return null;
    }
}
