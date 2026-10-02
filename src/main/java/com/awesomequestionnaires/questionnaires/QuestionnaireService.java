package com.awesomequestionnaires.questionnaires;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service 
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireService(
        QuestionnaireRepository repositoryParam
    ) {    
        this.questionnaireRepository = repositoryParam;
    }

    public UUID createQuestionnaire(String name, String description, QuestionnaireStatus questionnaireStatus) {
        boolean activeFIXED = true;
        Questionnaire questionnaire = new Questionnaire(name, description, questionnaireStatus, activeFIXED);
        Questionnaire saveNewQuestionnaire = this.questionnaireRepository.save(questionnaire);
        return saveNewQuestionnaire.getId();
    }

    public Page<Questionnaire> listAllQuestionnaires(Pageable pageable) {
        return questionnaireRepository.findAll(pageable);
    }

    public Questionnaire findOneQuestionnaireById(UUID questionnaireId) {
        return questionnaireRepository.findById(questionnaireId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionnaire not found"));
    }
}
