package com.awesomequestionnaires.questionnaires;

public record CreateQuestionnaireRequest(
    String name,
    String description,
    QuestionnaireStatus status
) {}
