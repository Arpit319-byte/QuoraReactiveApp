package com.example.QuoraReactiveApp.Service;

import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Mapper.Mapper;
import com.example.QuoraReactiveApp.Repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuestionService implements  IQuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    final private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository _questionRepository){
        this.questionRepository=_questionRepository;
        logger.info("QuestionService initialized with repository: {}", _questionRepository.getClass().getSimpleName());
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String questionId) {
        logger.debug("Attempting to fetch question with ID: {}", questionId);
        return questionRepository.findById(questionId)
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnSuccess(s -> logger.info("Question found successfully with ID: {}, Title: '{}'", questionId, s.getTitle()))
                .doOnError(e -> logger.error("Error occurred while fetching question with ID: {}. Error: {}", questionId, e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting database query for question ID: {}", questionId));
    }

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequest) {
        logger.info("Creating new question. Title: '{}', AuthorId: {}", questionRequest.getTitle(), questionRequest.getAuthorId());
        return questionRepository.save(Mapper.mapToQuestionEntity(questionRequest))
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnSuccess(s -> logger.info("Question created successfully with ID: {}, Title: '{}'", s.getId(), s.getTitle()))
                .doOnError(e -> logger.error("Error occurred while creating question. Title: '{}', AuthorId: {}, Error: {}", questionRequest.getTitle(), questionRequest.getAuthorId(), e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting database save operation for question: '{}'", questionRequest.getTitle()));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor,int page) {
        logger.info("Fetching all questions. Cursor: {}, Page size: {}", cursor, page);
        return questionRepository.findAll()
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnComplete(() -> logger.info("Successfully fetched all questions. Cursor: {}, Page size: {}", cursor, page))
                .doOnError(e -> logger.error("Error occurred while fetching all questions. Cursor: {}, Page size: {}, Error: {}", cursor, page, e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting database query for all questions"));
    }

    @Override
    public Mono<QuestionResponseDTO> updateQuestion(String questionId, QuestionRequestDTO questionRequest) {
        logger.info("Updating question with ID: {}. New Title: '{}', New Content length: {}", questionId, questionRequest.getTitle(), questionRequest.getContent().length());
        return questionRepository.findById(questionId)
                .doOnNext(existing -> logger.debug("Found existing question with ID: {}, Current Title: '{}'", questionId, existing.getTitle()))
                .flatMap(existingQuestion -> {
                    existingQuestion.setTitle(questionRequest.getTitle());
                    existingQuestion.setContent(questionRequest.getContent());
                    existingQuestion.setAuthorId(questionRequest.getAuthorId());
                    logger.debug("Updated question fields. New Title: '{}', New Content length: {}", existingQuestion.getTitle(), existingQuestion.getContent().length());
                    return questionRepository.save(existingQuestion);
                })
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnSuccess(s -> logger.info("Question updated successfully with ID: {}, New Title: '{}'", questionId, s.getTitle()))
                .doOnError(e -> logger.error("Error occurred while updating question with ID: {}. Error: {}", questionId, e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting update operation for question ID: {}", questionId));
    }

    @Override
    public Mono<Void> deleteQuestion(String questionId) {
        logger.info("Deleting question with ID: {}", questionId);
        return questionRepository.deleteById(questionId)
                .doOnSuccess(s -> logger.info("Question deleted successfully with ID: {}", questionId))
                .doOnError(e -> logger.error("Error occurred while deleting question with ID: {}. Error: {}", questionId, e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting delete operation for question ID: {}", questionId));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestion(String searchTerm, int offset, int pageSize) {
        logger.info("Searching questions with term: '{}', Offset: {}, Page size: {}", searchTerm, offset, pageSize);
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, pageSize))
                .map(Mapper::mapToQuestionResponseDTO)
                .doOnComplete(() -> logger.info("Search completed successfully for term: '{}', Offset: {}, Page size: {}", searchTerm, offset, pageSize))
                .doOnError(e -> logger.error("Error occurred while searching for term: '{}', Offset: {}, Page size: {}, Error: {}", searchTerm, offset, pageSize, e.getMessage()))
                .doOnSubscribe(s -> logger.debug("Starting search operation for term: '{}'", searchTerm));
    }
}
