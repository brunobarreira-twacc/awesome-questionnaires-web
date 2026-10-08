package com.awesomequestionnaires.questionnaires.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.awesomequestionnaires.questionnaires.database.repositories.QuestionRepository;
import com.awesomequestionnaires.questionnaires.dtos.request.CreateQuestionRequest;

@Service 
public class QuestionService {

    private static QuestionRepository questionRepository;

    public QuestionService(
        QuestionRepository questionRepository
    ) {
        this.questionRepository = questionRepository;
    }

    public List<UUID> createQuestions(UUID questionnaireId, List<CreateQuestionRequest> questionList) {
        return null;
    }
}
