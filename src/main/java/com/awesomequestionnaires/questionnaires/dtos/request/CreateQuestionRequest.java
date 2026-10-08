package com.awesomequestionnaires.questionnaires.dtos.request;

import com.awesomequestionnaires.questionnaires.database.models.QuestionType;

public record CreateQuestionRequest(
    QuestionType questionType,
    String displayText,
    Integer displayOrder
) {}
