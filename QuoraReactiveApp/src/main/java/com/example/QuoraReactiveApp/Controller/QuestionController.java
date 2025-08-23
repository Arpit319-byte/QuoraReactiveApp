package com.example.QuoraReactiveApp.Controller;


import com.example.QuoraReactiveApp.DTO.QuestionRequestDTO;
import com.example.QuoraReactiveApp.DTO.QuestionResponseDTO;
import com.example.QuoraReactiveApp.Service.IQuestionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    private IQuestionService questionService;

    public QuestionController(IQuestionService iQuestionService){
        this.questionService=iQuestionService;
    }


    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id);
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO);
    }

    @PutMapping("/{id}")
    public Mono<QuestionResponseDTO> updateQuestion(@RequestBody @Valid QuestionRequestDTO questionRequestDTO,@PathVariable String id){
        return questionService.updateQuestion(id,questionRequestDTO);
    }

    @DeleteMapping
    public Mono<Void> deleteQuestionById(@PathVariable String id){
        return questionService.deleteQuestion(id);
    }
}
