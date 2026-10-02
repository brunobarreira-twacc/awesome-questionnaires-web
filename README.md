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