package com.awesomequestionnaires.questionnaires.dtos.response;

import com.awesomequestionnaires.questionnaires.database.models.AnswerOption;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateAnswerOptionResponse(
        UUID id,
        UUID questionId,
        String displayText,
        Integer displayOrder,
        boolean status,
        OffsetDateTime createdAt,
        OffsetDateTime updateAt
) {
    public static CreateAnswerOptionResponse fromEntity(AnswerOption answerOption) {
        return new CreateAnswerOptionResponse(
                answerOption.getId(),
                answerOption.getQuestion().getId(),
                answerOption.getDisplayText(),
                answerOption.getDisplayOrder(),
                answerOption.getStatus(),
                answerOption.getCreatedAt(),
                answerOption.getUpdatedAt()
        );
    };
}
