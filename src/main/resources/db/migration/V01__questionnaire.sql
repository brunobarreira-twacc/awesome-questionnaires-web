CREATE TYPE questionnaire_status AS ENUM('PUBLICADO', 'RASCUNHO', 'DESCARTADO');

CREATE TABLE IF NOT EXISTS "questionnaires" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "name" VARCHAR(255),
    "description" VARCHAR(255),
    "status" questionnaire_status NOT NULL,
    "active" BOOLEAN,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);