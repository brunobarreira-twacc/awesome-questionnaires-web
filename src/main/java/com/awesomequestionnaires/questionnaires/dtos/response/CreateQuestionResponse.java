package com.awesomequestionnaires.questionnaires.dtos.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.awesomequestionnaires.questionnaires.database.models.Question;
import com.awesomequestionnaires.questionnaires.database.models.QuestionType;

public record CreateQuestionResponse(
    UUID id,
    UUID questionnaireId,
    QuestionType questionType,
    String displayText,
    Integer displayOrder,
    boolean status,
    OffsetDateTime createdAt,
    OffsetDateTime updateAt
) {
    public static CreateQuestionResponse fromEntity(Question question) {
        return new CreateQuestionResponse(
            question.getId(),
            question.getQuestionnaire().getId(),
            question.getQuestionType(),
            question.getDisplayText(),
            question.getDisplayOrder(),
            question.getStatus(),
            question.getCreatedAt(),
            question.getUpdatedAt()
        );
    }
}