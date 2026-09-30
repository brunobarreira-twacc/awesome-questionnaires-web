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