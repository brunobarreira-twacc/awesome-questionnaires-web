# AWESOME QUESTIONNAIRES WEB

## Requests

**Base URL:** `/api/v1`

### Criar um novo questionário
**Verbo:** POST
**Descrição:** Cria um novo questionário(sem perguntas em 30/09/2026). Para o campo status, utilize: RASCUNHO | PUBLICADO(preferido para esta rota) | DELETADO.
**Chamada de exemplo**
```
curl -XPOST -H "Content-type: application/json" -d '{
"name": "Qualquer_sring_valida",
"description": "Qualquer_descricao_valida",
"status": "PUBLICADO"
}' 'http://localhost:8080/api/v1/questionnaires' | jq
```

### Listar Questionários
**Verbo:** GET
**Descrição:** Listar todos questionários com paginação
**Chamada de exemplo**
```
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/api/v1/questionnaires'
```

### Listar um Questionário por Id
**Verbo:** GET
**Descrição:** Listar um questionário por Id(utilize UUID), retorna 404 quando o questionário não é encontrado
**Chamada de exemplo**
```
curl -XGET -H "Content-type: application/json" 'http://localhost:8080/api/v1/questionnaires/{questionnaireId}' | jq
```

### Criar Questões
```
curl -X POST -H "Content-type: application/json" -d '[
    {
        "questionType": "SINGLE_OPTION",
        "displayText": "Primeira questão?",
        "displayOrder": 1
    },
    {
        "questionType": "TEXT",
        "displayText": "Segunda questão?",
        "displayOrder": 2
    },
    {
        "questionType": "TEXT",
        "displayText": "Terceira questão?",
        "displayOrder": 3
    }
]' 'http://localhost:8080/api/v1/questionnaires/bf508689-794b-4d9b-bdb2-6360ce8423a0/questions'
```

### Criar Opções de resposta
```
curl -X POST "http://localhost:8080/api/v1/questionnaires/bf508689-794b-4d9b-bdb2-6360ce8423a0/questions/c70f44bf-88c7-4e53-ac66-b0fc7a06ca0a/answer-options" \
  -H "Content-Type: application/json" \
  -d '[
    {
        "displayText": "Primeira opcao",
        "displayOrder": 1
    },
    {
        "displayText": "Segunda opcao",
        "displayOrder": 2
    }
]'
```
