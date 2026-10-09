package com.awesomequestionnaires.questionnaires.database.repositories;

import com.awesomequestionnaires.questionnaires.database.models.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerOptionsRepository extends JpaRepository<AnswerOption, UUID> {
}
