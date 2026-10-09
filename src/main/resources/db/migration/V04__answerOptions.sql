CREATE TABLE IF NOT EXISTS "answer_options" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "question_id" UUID NOT NULL,
    "display_text" VARCHAR(255) NOT NULL,
    "display_order" INTEGER NOT NULL,
    "status" BOOLEAN NOT NULL DEFAULT true,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    CONSTRAINT fk_question
    FOREIGN KEY("question_id")
    REFERENCES "questions"("id"),

    CONSTRAINT unique_answer_option_display_order
    UNIQUE("question_id", "display_order"),

    CONSTRAINT check_answer_option_display_order
    CHECK("display_order">0)
    );