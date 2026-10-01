package com.awesomequestionnaires.questionnaires;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<Iterable<Questionnaire>> getAllQuestionnaires(
            @PageableDefault(size=50) Pageable paramsData
            ) {
        System.out.println(paramsData.getPageNumber());
        System.out.println(paramsData.getPageSize());
        var questionnaires = questionnaireService.listAllQuestionnaires();
        return ResponseEntity.status(HttpStatus.OK).body(questionnaires);
    }
}
