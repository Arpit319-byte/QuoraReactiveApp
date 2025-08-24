package com.example.QuoraReactiveApp.Controller;

import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Service.IQuestionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private IQuestionService questionService;

    public QuestionController(IQuestionService iQuestionService){
        this.questionService=iQuestionService;
        logger.info("QuestionController initialized with service: {}", iQuestionService.getClass().getSimpleName());
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        logger.info("GET request received for question with ID: {}", id);
        return questionService.getQuestionById(id)
                .doOnSuccess(result -> logger.info("Successfully retrieved question with ID: {}", id))
                .doOnError(error -> logger.error("Error retrieving question with ID: {}, Error: {}", id, error.getMessage()));
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestion(@RequestParam(required = false) String cursor,@RequestParam(defaultValue = "10") int size){
        logger.info("GET request received for all questions. Cursor: {}, Size: {}", cursor, size);
        return questionService.getAllQuestions(cursor,size)
                .doOnComplete(() -> logger.info("Successfully retrieved all questions. Cursor: {}, Size: {}", cursor, size))
                .doOnError(error -> logger.error("Error retrieving all questions. Cursor: {}, Size: {}, Error: {}", cursor, size, error.getMessage()));
    }

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO){
        logger.info("POST request received to create question. Title: {}, AuthorId: {}", questionRequestDTO.getTitle(), questionRequestDTO.getAuthorId());
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(result -> logger.info("Successfully created question with ID: {}, Title: {}", result.getId(), result.getTitle()))
                .doOnError(error -> logger.error("Error creating question. Title: {}, AuthorId: {}, Error: {}", questionRequestDTO.getTitle(), questionRequestDTO.getAuthorId(), error.getMessage()));
    }

    @PutMapping("/{id}")
    public Mono<QuestionResponseDTO> updateQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO,@PathVariable String id){
        logger.info("PUT request received to update question with ID: {}. New Title: {}", id, questionRequestDTO.getTitle());
        return questionService.updateQuestion(id,questionRequestDTO)
                .doOnSuccess(result -> logger.info("Successfully updated question with ID: {}, New Title: {}", id, result.getTitle()))
                .doOnError(error -> logger.error("Error updating question with ID: {}. Error: {}", id, error.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id){
        logger.info("DELETE request received for question with ID: {}", id);
        return questionService.deleteQuestion(id)
                .doOnSuccess(result -> logger.info("Successfully deleted question with ID: {}", id))
                .doOnError(error -> logger.error("Error deleting question with ID: {}. Error: {}", id, error.getMessage()));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions(
            @RequestParam String q,            // Search query parameter
            @RequestParam(defaultValue = "0") int offset,    // Pagination offset, defaults to 0 if not provided
            @RequestParam(defaultValue = "10") int size) {   // Number of results per page, defaults to 10 if not provided
        logger.info("SEARCH request received. Query: '{}', Offset: {}, Size: {}", q, offset, size);
        return questionService.searchQuestion(q, offset, size)
                .doOnComplete(() -> logger.info("Search completed successfully for query: '{}', Offset: {}, Size: {}", q, offset, size))
                .doOnError(error -> logger.error("Error during search. Query: '{}', Offset: {}, Size: {}, Error: {}", q, offset, size, error.getMessage()));
    }
}
