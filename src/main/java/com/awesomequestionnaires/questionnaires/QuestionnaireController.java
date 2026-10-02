package com.awesomequestionnaires.questionnaires;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/api/v1")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    public QuestionnaireController(
        QuestionnaireService questionnaireServiceParam
    ) {
        this.questionnaireService = questionnaireServiceParam;
    }

    @PostMapping("/questionnaires")
    public ResponseEntity<UUID> createQuestionnaire(
        @Valid @RequestBody CreateQuestionnaireRequest requestData
    ) {
        UUID newQuestionnaire = this.questionnaireService.createQuestionnaire(requestData.name(), requestData.description(), requestData.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionnaire);
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
