# 🧮 Calculadora Spring Boot

Uma aplicação REST com Spring Boot que implementa 10 operações matemáticas diferentes.

## 📌 Status da Atividade

✅ **CONCLUÍDO** - Todas as 10 funcionalidades implementadas e funcionando!

---

## 🚀 Como Executar

### 1. Inicie a aplicação:

```bash
cd "d:\if 4º periodo\bek\Calculadora\Calculadora"
.\mvnw.cmd spring-boot:run
```

Ou pelo VS Code:
1. Abra a pasta do projeto
2. Abra o terminal (Ctrl+`)
3. Execute o comando acima

A aplicação estará disponível em: **http://localhost:8080**

---

## 📋 Endpoints Disponíveis

### Base URL
```
http://localhost:8080
```

### 1️⃣ **Soma**
- **Endpoint:** `/soma/{n1}/{n2}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/soma/10/5` → **15**

### 2️⃣ **Subtração**
- **Endpoint:** `/subtracao/{n1}/{n2}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/subtracao/10/5` → **5**

### 3️⃣ **Multiplicação**
- **Endpoint:** `/multiplicacao/{n1}/{n2}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/multiplicacao/10/5` → **50**

### 4️⃣ **Divisão**
- **Endpoint:** `/divisao/{n1}/{n2}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/divisao/10/5` → **2**
- **Validação:** Rejeita divisão por zero

### 5️⃣ **Raiz Quadrada**
- **Endpoint:** `/raizQuadrada/{n1}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/raizQuadrada/9` → **3.0**
- **Validação:** Rejeita números negativos

### 6️⃣ **Raiz Cúbica**
- **Endpoint:** `/raizCubica/{n1}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/raizCubica/8` → **2.0**

### 7️⃣ **Potência**
- **Endpoint:** `/potencia/{base}/{expoente}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/potencia/2/3` → **8.0** (2³ = 8)

### 8️⃣ **Conversão para Binário**
- **Endpoint:** `/binario/{n1}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/binario/13` → **1101**

### 9️⃣ **Potência de 10**
- **Endpoint:** `/potencia10/{expoente}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/potencia10/3` → **1000.0** (10³ = 1000)

### 🔟 **Fatorial**
- **Endpoint:** `/fatorial/{n1}`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/fatorial/5` → **120** (5! = 120)
- **Validação:** Rejeita números negativos

### 🥧 **Valor de Pi**
- **Endpoint:** `/pi`
- **Método:** GET
- **Exemplo:** `http://localhost:8080/pi` → **3.141592653589793**

---

## 🎯 Testando Rápido no Navegador

| Operação | Cole no Navegador | Resultado |
|----------|-------------------|-----------|
| 5 + 3 | `localhost:8080/soma/5/3` | 8 |
| 10 - 4 | `localhost:8080/subtracao/10/4` | 6 |
| 7 × 8 | `localhost:8080/multiplicacao/7/8` | 56 |
| 20 ÷ 4 | `localhost:8080/divisao/20/4` | 5 |
| √16 | `localhost:8080/raizQuadrada/16` | 4.0 |
| ∛27 | `localhost:8080/raizCubica/27` | 3.0 |
| 3⁴ | `localhost:8080/potencia/3/4` | 81.0 |
| 13 em binário | `localhost:8080/binario/13` | 1101 |
| 10² | `localhost:8080/potencia10/2` | 100.0 |
| 6! | `localhost:8080/fatorial/6` | 720 |
| π | `localhost:8080/pi` | 3.14... |

---

## 🔍 Validações Implementadas

✓ **Validação de entrada:** 
- Todos os parâmetros são validados como números
- Aceita números inteiros e decimais
- Aceita vírgulas e pontos como separador decimal
- Aceita números negativos (quando aplicável)

✓ **Validações específicas:**
- **Divisão:** Valida divisão por zero
- **Raiz Quadrada:** Valida números negativos
- **Fatorial:** Valida números negativos

✓ **Tratamento de erros:** Exceções são levantadas para entradas inválidas

---

## 📁 Estrutura do Projeto

```
Calculadora/
├── src/
│   ├── main/
│   │   ├── java/br/nando/Calculadora/
│   │   │   ├── CalculadoraApplication.java (Classe principal)
│   │   │   └── MathController.java (Todos os endpoints)
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/br/nando/Calculadora/
│           └── CalculadoraApplicationTests.java
├── pom.xml (Dependências do Maven)
├── README.md (Este arquivo)
└── ENDPOINTS.md (Documentação detalhada)
```

---

## 🛠️ Tecnologias Usadas

- **Java 17**
- **Spring Boot 4.0.5**
- **Maven** (Build tool)
- **REST API**

---

## ✅ Checklist de Funcionalidades

- ✅ Soma
- ✅ Subtração
- ✅ Multiplicação
- ✅ Divisão
- ✅ Raiz Quadrada
- ✅ Raiz Cúbica
- ✅ Potência
- ✅ Conversão para Binário
- ✅ Potência de 10
- ✅ Fatorial
- ✅ Valor de Pi

---

## 📝 Próximos Passos (Próxima Aula)

De acordo com o professor, as próximas implementações serão:
- ⏳ Reorganização para o padrão **MVC** (Model-View-Controller)
- ⏳ Implementação de **Controle de Exceções** personalizado

**IMPORTANTE:** Esta atividade é **SEM MVC**. O padrão MVC será implementado na próxima aula!

---

## 🛑 Como Parar a Aplicação

No VS Code, abra o Terminal e pressione: **Ctrl+C**

---

**Versão:** 1.0 | **Data:** 26/03/2026 | **Status:** ✅ Completo
