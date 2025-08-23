package com.example.QuoraReactiveApp.Service;

import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Mapper.Mapper;
import com.example.QuoraReactiveApp.Repository.QuestionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuestionService implements  IQuestionService {

    final private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository _questionRepository){
        this.questionRepository=_questionRepository;
    }


    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String questionId) {
       return questionRepository.findById(questionId)
               .map(Mapper::mapToQuestionResponseDTO)
               .doOnSuccess(s-> System.out.println("Question found with id: "+questionId))
               .doOnError(e-> System.out.println("Error occurred while fetching question with id: "+questionId+" Error: "+e.getMessage()));

    }

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequest) {
        return  questionRepository.save(Mapper.mapToQuestionEntity(questionRequest))
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnSuccess(s-> System.out.println("Question created with id: "+s.getId()))
                .doOnError(e-> System.out.println("Error occurred while creating question. Error: "+e.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return  questionRepository.findAll()
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnComplete(()-> System.out.println("Fetched all questions"))
                .doOnError(e-> System.out.println("Error occurred while fetching all questions. Error: "+e.getMessage()));
    }

    @Override
    public Mono<QuestionResponseDTO> updateQuestion(String questionId, QuestionRequestDTO questionRequest) {
        return questionRepository.findById(questionId)
                .flatMap(existingQuestion -> {
                    existingQuestion.setTitle(questionRequest.getTitle());
                    existingQuestion.setContent(questionRequest.getContent());
                    existingQuestion.setAuthorId(questionRequest.getAuthorId());
                    return questionRepository.save(existingQuestion);
                })
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnSuccess(s-> System.out.println("Question updated with id: "+questionId))
                .doOnError(e-> System.out.println("Error occurred while updating question with id: "+questionId+" Error: "+e.getMessage()));
    }

    @Override
    public Mono<Void> deleteQuestion(String questionId) {
        return questionRepository.deleteById(questionId)
                .doOnSuccess(s-> System.out.println("Question deleted with id: "+questionId))
                .doOnError(e-> System.out.println("Error occurred while deleting question with id: "+questionId+" Error: "+e.getMessage()));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestion(String searchTerm, int offset, int pageSize) {
        return  questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, pageSize))
                  .map(Mapper::mapToQuestionResponseDTO)
                  .doOnComplete(()-> System.out.println("Search completed for term: "+searchTerm))
                  .doOnError(e-> System.out.println("Error occurred while searching for term: "+searchTerm+" Error: "+e.getMessage()));
    }
}
