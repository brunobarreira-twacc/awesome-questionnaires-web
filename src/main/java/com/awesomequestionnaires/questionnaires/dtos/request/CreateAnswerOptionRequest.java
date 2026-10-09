package com.awesomequestionnaires.questionnaires.dtos.request;

public record CreateAnswerOptionRequest(
        String displayText,
        Integer displayOrder
) {
}
