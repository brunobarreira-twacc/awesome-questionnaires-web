package com.awesomequestionnaires.questionnaires.database.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="answer_options")
public class AnswerOption {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="display_text")
    private String displayText;

    @Column(name="display_order")
    private Integer displayOrder;

    @Column(name="status", nullable = false)
    private boolean status;

    @Column(name="created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="question_id", nullable = false)
    private Question question;

    public AnswerOption() {}

    public AnswerOption(String displayText, Integer displayOrder) {
        this.displayText = displayText;
        this.displayOrder = displayOrder;
    }

    public UUID getId() {
        return id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public boolean getStatus() {
        return status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Question getQuestion() { return question; }
}
