package com.awesomequestionnaires.questionnaires;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
        @RequestBody CreateQuestionnaireRequest requestData
    ) {
        UUID newQuestionnaire = this.questionnaireService.createQuestionnaire(requestData.name(), requestData.description(), requestData.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuestionnaire);
    }
}
