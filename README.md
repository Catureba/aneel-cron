
# Relatório de Empreendimentos ANEEL

Este projeto é uma aplicação Spring Boot 3.4 com Kotlin e Maven que consome um CSV público da ANEEL contendo dados sobre usinas geradoras de energia, armazena no banco de dados e expõe endpoints REST para manipulação e consulta desses dados.

## Funcionalidades

- Baixar e importar automaticamente dados do CSV da ANEEL
- Persistir os dados em banco com registro de data/hora (`created_at`)
- Listar os 5 maiores geradores de energia até o momento
- Swagger UI para testar a API
- Agendamento automático diário para atualizar os dados

## Endpoints

- `POST /relatorio/atualizar` — Atualiza os dados manualmente
- `GET /relatorio/top5` — Retorna os 5 maiores geradores do dia
- `DELETE /relatorio/limpar` — Limpa todos os dados do banco

## Swagger

Após iniciar a aplicação, acesse a documentação da API em:

```
http://localhost:8080/swagger-ui.html
```

## Agendamento (Scheduler)

O sistema atualiza automaticamente os dados uma vez por dia às 3h da manhã.

## Tecnologias

- Kotlin
- Spring Boot
- PostgreSQL
- SpringDoc OpenAPI (Swagger)

## Como rodar

1. Clone o projeto
2. Configure o banco de dados no `application.yml`
3. Rode a aplicação com o comando:

```
./mvnw spring-boot:run
```

## Autor

Mateus Catureba - Projeto demonstrativo de integração com dados públicos da ANEEL para teste tecnico da Bolt Energy.
