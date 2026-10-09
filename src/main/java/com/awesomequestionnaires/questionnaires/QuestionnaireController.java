package com.awesomequestionnaires.questionnaires;

import com.awesomequestionnaires.questionnaires.dtos.request.CreateAnswerOptionRequest;
import com.awesomequestionnaires.questionnaires.dtos.response.CreateAnswerOptionResponse;
import com.awesomequestionnaires.questionnaires.services.AnswerOptionsService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.awesomequestionnaires.questionnaires.dtos.request.CreateQuestionRequest;
import com.awesomequestionnaires.questionnaires.dtos.response.CreateQuestionResponse;
import com.awesomequestionnaires.questionnaires.services.QuestionService;

import java.util.List;
import java.util.UUID;
@Controller
@RequestMapping("/api/v1")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    private final QuestionService questionService;
    private final AnswerOptionsService answerOptionsService;

    public QuestionnaireController(
        QuestionnaireService questionnaireServiceParam,
        QuestionService questionService,
        AnswerOptionsService answerOptionsService
    ) {
        this.questionnaireService = questionnaireServiceParam;
        this.questionService = questionService;
        this.answerOptionsService = answerOptionsService;
    }

    @PostMapping("/questionnaires")
    public ResponseEntity<UUID> createQuestionnaire(
        @Valid @RequestBody CreateQuestionnaireRequest requestData
    ) {
        UUID newQuestionnaire = this.questionnaireService.createQuestionnaire(requestData.name(), requestData.description(), requestData.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionnaire);
    }

    @PostMapping("/questionnaires/{questionnaireId}/questions")
    public ResponseEntity<List<CreateQuestionResponse>> createQuestion(
        @PathVariable("questionnaireId") UUID questionnaireId,
        @RequestBody List<CreateQuestionRequest> requestData
    ) {
        List<CreateQuestionResponse> questions = this.questionService.createQuestions(questionnaireId, requestData);
        return ResponseEntity.status(HttpStatus.CREATED).body(questions);
    }

    @PostMapping("/questionnaires/{questionnaireId}/questions/{questionId}/answer-options")
    public ResponseEntity<List<CreateAnswerOptionResponse>> createAnswerOption(
            @PathVariable("questionnaireId") UUID questionnaireId,
            @PathVariable("questionId") UUID questionId,
            @RequestBody List<CreateAnswerOptionRequest> requestData
    ) {
        List<CreateAnswerOptionResponse> answerOptions = this.answerOptionsService.createAnswerOption(questionnaireId, questionId, requestData);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerOptions);
    }

    @GetMapping("/questionnaires")
    public ResponseEntity<PagedModel<Questionnaire>> getAllQuestionnaires(
            @PageableDefault(size=50) Pageable pageable
            ) {
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        var questionnaires = questionnaireService.listAllQuestionnaires(pageable);
        PagedModel<Questionnaire> response = new PagedModel<>(questionnaires);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/questionnaires/{questionnaireId}")
    public ResponseEntity<Questionnaire> getQuestionnaireById(@PathVariable("questionnaireId") UUID questionnaireId) {
        Questionnaire response = this.questionnaireService.findOneQuestionnaireById(questionnaireId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
