package com.awesomequestionnaires.questionnaires;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
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

    @CreationTimestamp
    private OffsetDateTime created_at;

    @UpdateTimestamp
    private OffsetDateTime updated_at;

    public Questionnaire() {}
}