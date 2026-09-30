package com.awesomequestionnaires.questionnaires;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, UUID> {}
