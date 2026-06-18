# GestaoPessoas2026

API REST em Spring Boot para simular operações sobre pessoas, com foco em injeção de dependência, separação em camadas e tratamento centralizado de exceções.

## Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring Web
- Spring Boot DevTools
- Maven

## Estrutura de pacotes

- `com.souza.gestaopessoas2026.controller`
- `com.souza.gestaopessoas2026.services`
- `com.souza.gestaopessoas2026.model`
- `com.souza.gestaopessoas2026.exceptions`
- `com.souza.gestaopessoas2026.handle`

## Funcionalidades

- Cadastro de pessoa
- Consulta de pessoa por id
- Listagem de pessoas
- Atualização de pessoa
- Remoção de pessoa
- Tratamento de erros 400, 401 e 500

## Como executar

Na pasta do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows, também é possível usar:

```bash
mvnw.cmd spring-boot:run
```

A aplicação sobe em:

```text
http://localhost:8080
```

## Endpoints

### Pessoa

- `GET /pessoas/{id}`
- `GET /pessoas`
- `POST /pessoas`
- `PUT /pessoas`
- `DELETE /pessoas/{id}`

### Teste de exceções

- `GET /teste/int/{v1}`
- `GET /teste/bad/{v1}`
- `GET /teste/nao/{v1}`

## Exemplo de JSON

Para `POST /pessoas` e `PUT /pessoas`:

```json
{
  "id": 1,
  "nome": "Maria",
  "sobrenome": "Silva",
  "endereco": "Rua A, 123",
  "genero": "Feminino"
}
```

## Exemplos de teste

### Criar pessoa

```http
POST http://localhost:8080/pessoas
Content-Type: application/json

{
  "nome": "Maria",
  "sobrenome": "Silva",
  "endereco": "Rua A, 123",
  "genero": "Feminino"
}
```

### Buscar por id

```http
GET http://localhost:8080/pessoas/1
```

### Testar erro interno

```http
GET http://localhost:8080/teste/int/erro
```

### Testar bad request

```http
GET http://localhost:8080/teste/bad/invalido
```

### Testar unauthorized

```http
GET http://localhost:8080/teste/nao/bloqueado
```

## Arquivos úteis

- [requests.http](requests.http)
- [uml-mermaid.md](uml-mermaid.md)
