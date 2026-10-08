package com.awesomequestionnaires.questionnaires.database.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awesomequestionnaires.questionnaires.database.models.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {}  
