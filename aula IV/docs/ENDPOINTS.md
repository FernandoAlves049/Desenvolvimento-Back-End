# Documentação da API da Calculadora

Este documento lista todos os endpoints disponíveis na aplicação Spring Boot da Calculadora.

## Base URL
```
http://localhost:8080
```

---

## 📋 Endpoints Disponíveis

### 1️⃣ **Soma**
- **Endpoint:** `/soma/{n1}/{n2}`
- **Método:** GET
- **Descrição:** Realiza a soma de dois números
- **Exemplo:** 
  ```
  http://localhost:8080/soma/10/5
  Resultado: 15
  ```

### 2️⃣ **Subtração**
- **Endpoint:** `/subtracao/{n1}/{n2}`
- **Método:** GET
- **Descrição:** Realiza a subtração de dois números
- **Exemplo:**
  ```
  http://localhost:8080/subtracao/10/5
  Resultado: 5
  ```

### 3️⃣ **Multiplicação**
- **Endpoint:** `/multiplicacao/{n1}/{n2}`
- **Método:** GET
- **Descrição:** Realiza a multiplicação de dois números
- **Exemplo:**
  ```
  http://localhost:8080/multiplicacao/10/5
  Resultado: 50
  ```

### 4️⃣ **Divisão**
- **Endpoint:** `/divisao/{n1}/{n2}`
- **Método:** GET
- **Descrição:** Realiza a divisão de dois números (valida divisão por zero)
- **Exemplo:**
  ```
  http://localhost:8080/divisao/10/5
  Resultado: 2
  ```

### 5️⃣ **Raiz Quadrada**
- **Endpoint:** `/raizQuadrada/{n1}`
- **Método:** GET
- **Descrição:** Calcula a raiz quadrada de um número
- **Exemplo:**
  ```
  http://localhost:8080/raizQuadrada/9
  Resultado: 3.0
  ```

### 6️⃣ **Raiz Cúbica**
- **Endpoint:** `/raizCubica/{n1}`
- **Método:** GET
- **Descrição:** Calcula a raiz cúbica de um número (Ex: raiz cúbica de 8 = 2)
- **Exemplo:**
  ```
  http://localhost:8080/raizCubica/8
  Resultado: 2.0
  ```

### 7️⃣ **Potência**
- **Endpoint:** `/potencia/{base}/{expoente}`
- **Método:** GET
- **Descrição:** Eleva um número a uma potência (Ex: 2 elevado ao cubo = 8)
- **Exemplo:**
  ```
  http://localhost:8080/potencia/2/3
  Resultado: 8.0
  ```

### 8️⃣ **Conversão para Binário**
- **Endpoint:** `/binario/{n1}`
- **Método:** GET
- **Descrição:** Converte um número decimal para binário (Ex: Decimal 13 = Binário 1101)
- **Exemplo:**
  ```
  http://localhost:8080/binario/13
  Resultado: 1101
  ```

### 9️⃣ **Potência de 10**
- **Endpoint:** `/potencia10/{expoente}`
- **Método:** GET
- **Descrição:** Eleva 10 a uma potência (Ex: 10 elevado a 3 = 1000)
- **Exemplo:**
  ```
  http://localhost:8080/potencia10/3
  Resultado: 1000.0
  ```

### 🔟 **Fatorial**
- **Endpoint:** `/fatorial/{n1}`
- **Método:** GET
- **Descrição:** Calcula o fatorial de um número (Ex: Fatorial de 5 = 120)
- **Exemplo:**
  ```
  http://localhost:8080/fatorial/5
  Resultado: 120
  ```

### 🥧 **Pi - Valor de Pi**
- **Endpoint:** `/pi`
- **Método:** GET
- **Descrição:** Retorna o valor de Pi (π ≈ 3.141592653)
- **Exemplo:**
  ```
  http://localhost:8080/pi
  Resultado: 3.141592653589793
  ```

---

## 🔍 Validações Implementadas

✓ **Validação de entrada:** Todos os parâmetros são validados para garantir que sejam números
- Aceita números inteiros e decimais
- Aceita vírgulas e pontos como separador decimal
- Aceita números negativos (quando aplicável)

✓ **Validações específicas:**
- **Divisão:** Valida divisão por zero
- **Raiz Quadrada:** Valida números negativos
- **Fatorial:** Valida números negativos

✓ **Tratamento de erros:** Exceções são levantadas para entradas inválidas

---

## 💡 Como Usar

1. Inicie a aplicação Spring Boot
2. Use um navegador ou ferramenta como Postman para fazer requisições GET
3. Substitua os valores de `{n1}`, `{n2}` ou `{expoente}` pelos números desejados

### Exemplos com `curl`:

```bash
# Soma
curl http://localhost:8080/soma/10/5

# Raiz Quadrada
curl http://localhost:8080/raizQuadrada/16

# Fatorial
curl http://localhost:8080/fatorial/5

# Valor de Pi
curl http://localhost:8080/pi
```

---

## 📝 Próximas Implementações

Conforme solicitado, as próximas aulas incluirão:
- ✅ Implementação de TODAS as funcionalidades (CONCLUÍDO)
- ⏳ Reorganização para o padrão MVC
- ⏳ Controle de Exceções personalizado

---

Versão: 1.0 | Data: 26/03/2026
