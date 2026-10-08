package com.awesomequestionnaires.questionnaires.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.awesomequestionnaires.questionnaires.Questionnaire;
import com.awesomequestionnaires.questionnaires.QuestionnaireRepository;
import com.awesomequestionnaires.questionnaires.database.models.Question;
import com.awesomequestionnaires.questionnaires.database.repositories.QuestionRepository;
import com.awesomequestionnaires.questionnaires.dtos.request.CreateQuestionRequest;
import com.awesomequestionnaires.questionnaires.dtos.response.CreateQuestionResponse;

@Service 
public class QuestionService {

    private final static boolean NEW_QUESTIONS_STATUS = true;
    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(
        QuestionnaireRepository questionnaireRepository,
        QuestionRepository questionRepository
    ) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional 
    public List<CreateQuestionResponse> createQuestions(UUID questionnaireId, List<CreateQuestionRequest> questionList) {

        // 1 - PESQUISAR SE UM QUESTIONÁRIO EXISTE POR ID
        Questionnaire questionnaire = this.findOneQuestionnaireById(questionnaireId);

        // 2 - TRANSFORMAR A LISTA DE QUESTOES DO TIPO DTO PARA O TIPO ENTIDADE QUESTION
        List<Question> questionsToCreate = questionList.stream()
            .map(question -> new Question(
                question.questionType(),
                question.displayText(),
                question.displayOrder(),
                NEW_QUESTIONS_STATUS,
                questionnaire
            ))
            .toList();

        // 3 - SALVAR QUESTÕES NO BANCO DE DADOS VINCULADAS AO QUESTIONARIO
        List<Question> savedQuestions = this.questionRepository.saveAll(questionsToCreate);


        // 4 - TRANSFORMAR AS ENTIDADES QUESTION SALVAS NO BANCO PARA O DTO DE RESPOSTA
        return savedQuestions.stream()
            .map(CreateQuestionResponse::fromEntity)
            .toList();
    }

    public Questionnaire findOneQuestionnaireById(UUID questionnaireId) {
        return questionnaireRepository.findById(questionnaireId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionnaire not found"));
    }
}
