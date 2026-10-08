CREATE TYPE question_type AS ENUM('SINGLE_OPTION', 'MULTIPLE_OPTION', 'TEXT', 'NUMBER', 'CONDITIONAL');

CREATE TABLE IF NOT EXISTS "questions" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "questionnaire_id" UUID NOT NULL,
    "display_text" VARCHAR(255) NOT NULL,
    "display_order" INTEGER NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT true,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    CONSTRAINT fk_questionnaires
        FOREIGN KEY("questionnaire_id")
        REFERENCES "questionnaires"("id"),

    CONSTRAINT unique_questions_display_order
        UNIQUE("questionnaire_id", "display_order"),

    CONSTRAINT check_question_display_order
        CHECK("display_order">0)
);