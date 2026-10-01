package com.awesomequestionnaires.questionnaires;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateQuestionnaireRequest(

        @NotBlank(message="Nome é um campo obrigatório")
        @Size(min=5, max=255, message="O nome deve conter entre 5 e 255 caracteres")
        String name,

        @NotBlank(message="Descrição é um campo obrigatório")
        @Size(min=5, max=255, message="A descrição deve conter entre 5 e 255 caracteres")
        String description,

        @NotNull(message="Status é obrigatório")
        QuestionnaireStatus status
) {}
