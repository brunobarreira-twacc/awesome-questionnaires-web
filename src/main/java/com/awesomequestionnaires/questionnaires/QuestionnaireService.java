package com.awesomequestionnaires.questionnaires;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.awesomequestionnaires.questionnaires.database.models.Question;
import com.awesomequestionnaires.questionnaires.database.repositories.AnswerOptionsRepository;
import com.awesomequestionnaires.questionnaires.database.repositories.QuestionRepository;
import com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse.AnswerOptionResponse;
import com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse.QuestionResponse;
import com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse.QuestionnaireDetailResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service 
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionsRepository answerOptionRepository;

    public QuestionnaireService(
        QuestionnaireRepository repositoryParam,
        QuestionRepository questionRepository,
        AnswerOptionsRepository answerOptionRepository
    ) {    
        this.questionnaireRepository = repositoryParam;
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
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

    public QuestionnaireDetailResponse findDetailedQuestionnaireById(UUID questionnaireId) {
        Questionnaire questionnaire = this.findQuestionnaireById(questionnaireId);
        
        List<Question> questionEntities = this.findQuestionByQuestionnaireId(questionnaireId);
        
        List<UUID> questionIds = questionEntities.stream()
            .map(Question::getId)
            .toList();

        Map<UUID, List<AnswerOptionResponse>> optionsByQuestionId = 
            questionIds.isEmpty()
                ? Map.of()
                : answerOptionRepository
                    .findByQuestionIdIn(questionIds)
                    .stream()
                    .collect(Collectors.groupingBy(
                        option -> option.getQuestion().getId(),
                        Collectors.mapping(AnswerOptionResponse::fromEntity, Collectors.toList())
                    ));

        List<QuestionResponse> questions = questionEntities.stream()
                    .map(question -> new QuestionResponse(
                        question.getId(),
                        question.getQuestionType(),
                        question.getDisplayText(),
                        question.getDisplayOrder(),
                        question.getStatus(),
                        optionsByQuestionId.getOrDefault(question.getId(), List.of())
                    ))
                    .toList();

        return new QuestionnaireDetailResponse(questionnaireId, questionnaire.getName(), questionnaire.getDescription(), questionnaire.getStatus(), questionnaire.isActive(), questions);
    }

    private Questionnaire findQuestionnaireById(UUID questionnaireId) {
        return this.questionnaireRepository
            .findById(questionnaireId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Questionnaire not found. Please, use a different Questionnaire UUID and try again."
        ));
    }

    private List<Question> findQuestionByQuestionnaireId(UUID questionnaireId) {
        return this.questionRepository
            .findByQuestionnaireId(questionnaireId);
    }
}