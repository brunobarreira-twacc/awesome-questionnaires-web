package com.awesomequestionnaires.questionnaires.dtos.response.questionnaireDetailResponse;

import java.util.List;
import java.util.UUID;

import com.awesomequestionnaires.questionnaires.QuestionnaireStatus;

public record QuestionnaireDetailResponse(
    UUID id,
    String name,
    String description,
    QuestionnaireStatus status,
    boolean active,
    List<QuestionResponse> questions
) {}
