package com.awesomequestionnaires.questionnaires;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.awesomequestionnaires.questionnaires.database.models.Question;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="questionnaires")
public class Questionnaire {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private QuestionnaireStatus status;

    @Column
    private boolean active;

    @Column(name="created_at")
    @CreationTimestamp
    private OffsetDateTime created_at;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updated_at;

    @OneToMany(mappedBy = "questionnaire")
    private List<Question> questions = new ArrayList<>();

    public Questionnaire() {}

    public Questionnaire(String name, String description, QuestionnaireStatus status, boolean active) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public QuestionnaireStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }
}