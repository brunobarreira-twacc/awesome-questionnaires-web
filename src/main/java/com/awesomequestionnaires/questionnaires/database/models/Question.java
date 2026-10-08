package com.awesomequestionnaires.questionnaires.database.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.awesomequestionnaires.questionnaires.Questionnaire;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue
    private UUID id;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @Column(name="question_type", nullable = false)
    private String questionType;

    @Column(name="display_text")
    private String displayText;

    @Column(name="display_order")
    private String displayOrder;

    @Column(name="status", nullable = false)
    private boolean status;

    @Column(name="created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    public Question() {
    }

    public Question(String questionType, String displayText, String displayOrder, boolean status,
            Questionnaire questionnaire) {
        this.questionType = questionType;
        this.displayText = displayText;
        this.displayOrder = displayOrder;
        this.status = status;
        this.questionnaire = questionnaire;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public boolean isStatus() {
        return status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }
}
