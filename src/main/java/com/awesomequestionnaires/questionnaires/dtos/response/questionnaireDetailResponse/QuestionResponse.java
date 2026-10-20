package com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse;

import java.util.List;
import java.util.UUID;

import com.awesomequestionnaires.questionnaires.database.models.QuestionType;

public record QuestionResponse(
    UUID id,
    QuestionType questionType,
    String displayText,
    Integer displayOrder,
    boolean status,
    List<AnswerOptionResponse> answerOptions
) {}
