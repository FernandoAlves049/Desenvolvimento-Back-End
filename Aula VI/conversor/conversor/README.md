# Conversor de Temperaturas

API REST em Spring Boot para conversao de unidades de temperatura, seguindo o modelo MVC e mantendo a regra de negocio separada do controller.

## Funcionalidades

- Conversao de Celsius para Kelvin
- Conversao de Celsius para Fahrenheit
- Conversao de Kelvin para Celsius
- Conversao de Kelvin para Fahrenheit
- Conversao de Fahrenheit para Celsius
- Conversao de Fahrenheit para Kelvin
- Validacao de entrada numerica na URL
- Tratamento global de excecoes com resposta padronizada em JSON

## Tecnologias

- Java
- Spring Boot
- Spring Web MVC
- Maven

## Como executar

### Pre-requisitos

- Java 17 ou superior
- Maven Wrapper incluso no projeto

### Rodando a aplicacao

```bash
./mvnw spring-boot:run
```

No Windows, voce tambem pode usar:

```bash
./mvnw.cmd spring-boot:run
```

A aplicacao sobe em:

```text
http://localhost:8080
```

## Endpoints

Todos os valores devem ser enviados como Path Variable na URL.

### Celsius

- GET `/conversor/celsiusParaKelvin/{value}`
- GET `/conversor/celsiusParaFahrenheit/{value}`

### Kelvin

- GET `/conversor/kelvinParaCelsius/{value}`
- GET `/conversor/kelvinParaFahrenheit/{value}`

### Fahrenheit

- GET `/conversor/fahrenheitParaCelsius/{value}`
- GET `/conversor/fahrenheitParaKelvin/{value}`

## Exemplos de uso

```bash
GET http://localhost:8080/conversor/celsiusParaKelvin/0
```

Resposta esperada:

```json
273.15
```

```bash
GET http://localhost:8080/conversor/fahrenheitParaCelsius/32
```

Resposta esperada:

```json
0.0
```

## Erros

Se o valor enviado na URL nao for numerico, a API retorna `400 Bad Request` com uma resposta padronizada contendo data, mensagem e detalhes.

Exemplo:

```bash
GET http://localhost:8080/conversor/celsiusParaKelvin/abc
```

## Testes

Para executar os testes:

```bash
./mvnw test
```

## Estrutura principal

- `src/main/java/com/suaapi/conversor/controller/ConversorController.java`
- `src/main/java/com/suaapi/conversor/TemperatureOperator.java`
- `src/main/java/com/suaapi/conversor/NumberConverter.java`
- `src/main/java/com/suaapi/conversor/handler/CustomizedResponseEntityExceptionHandler.java`
