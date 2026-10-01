package com.awesomequestionnaires.questionnaires;

import org.springframework.stereotype.Service;

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

    public Iterable<Questionnaire> listAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }
}
