package com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse;

import java.util.UUID;

import com.awesomequestionnaires.questionnaires.database.models.AnswerOption;

public record AnswerOptionResponse(
    UUID id,
    String displayText,
    Integer displayOrder,
    boolean status
) {
    public static AnswerOptionResponse fromEntity(AnswerOption answerOption) {
        return new AnswerOptionResponse(
            answerOption.getId(),
            answerOption.getDisplayText(),
            answerOption.getDisplayOrder(),
            answerOption.getStatus()
        );
    }
}
