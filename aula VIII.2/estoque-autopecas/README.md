# Gerenciador de Estoque de Autopeças

Este é um projeto que desenvolvi para o gerenciamento de estoque de autopeças integrado com banco de dados MySQL. A aplicação permite realizar o cadastro, consulta, atualização e exclusão (CRUD) de produtos, armazenando informações essenciais como nome da peça, descrição, preço, quantidade em estoque, peso e tamanho.

A arquitetura foi estruturada com **Spring Boot (Spring Data JPA, Spring Web MVC)** e renderização dinâmica de telas com **Thymeleaf**, acompanhado de uma estilização premium em **CSS Vanilla** com design Dark Mode e Glassmorphism.

---

# Testes de Funcionamento da Aplicação

Para validar o funcionamento da aplicação, verificar a integração com o banco de dados MySQL e atestar as regras de validação implementadas, realizei testes automatizados e testes manuais que cobrem todas as operações de cadastro, consulta, alteração e exclusão (CRUD) de autopeças.

## 1. Testes Automatizados (19 casos de teste executados com sucesso)

Eu desenvolvi testes automatizados para validar separadamente cada camada da aplicação utilizando um banco de dados H2 em memória (`src/test/resources/application.properties`), garantindo que o fluxo estivesse correto de forma isolada:

### Testes de Validação e Negócio (`ProdutoServiceTest.java`)

- **Cadastro com Sucesso**: Validei o salvamento correto de autopeças contendo todos os dados válidos.
- **Validação de Nome**: Testei e confirmei que tentar cadastrar uma peça com nome vazio ou nulo lança uma exceção com a mensagem `"O nome da peça é obrigatório e não pode ser vazio."`.
- **Validação de Tamanho**: Testei e confirmei que tentar cadastrar uma peça com tamanho vazio ou nulo lança uma exceção com a mensagem `"O tamanho é obrigatório e não pode ser vazio."`.
- **Validação de Preço**: Testei e confirmei que preços menores ou iguais a zero são recusados com a mensagem `"O preço deve ser maior que zero."`.
- **Validação de Quantidade**: Testei e confirmei que quantidades negativas no estoque geram a exceção `"A quantidade em estoque não pode ser negativa."`.
- **Validação de Peso**: Testei e confirmei que pesos menores ou iguais a zero são rejeitados com a mensagem `"O peso deve ser maior que zero."`.
- **Operação de Exclusão**: Testei a deleção de peças existentes e a tentativa de exclusão de IDs inexistentes, garantindo que o erro adequado seja lançado.

### Testes de Interface e Rotas (`ProdutoControllerTest.java`)

- **Redirecionamento**: Testei e validei que o acesso à rota raiz `/` redireciona automaticamente para `/produtos`.
- **Listagem e Painel**: Testei a renderização da página `lista.html` com o modelo de dados populado, confirmando o cálculo matemático correto de estatísticas (Total de Peças, Valor Total, Variedade de Itens e Alertas de Estoque Baixo).
- **Filtro de Busca**: Testei a passagem de parâmetros de busca e a filtragem correta no repositório.
- **Formulário de Cadastro**: Validei a renderização correta da tela de criação de peças.
- **Comportamento do Formulário e Feedbacks**: Testei o envio com sucesso (que redireciona para a lista exibindo mensagem flash de sucesso) e o envio com erros de validação (que retorna para o formulário exibindo o alerta de erro em vermelho).
- **Edição e Exclusão**: Validei o carregamento de peças existentes na tela de edição e o redirecionamento com mensagens de erro para IDs inválidos.

---

## 2. Testes Manuais e Integração com Banco de Dados

Para validar o fluxo de funcionamento real integrado ao banco de dados MySQL, eu executei a aplicação e segui os passos abaixo:

### Verificação da Integração com o Banco de Dados

- Configurei as credenciais (`root` / `707070.hh`) no arquivo `application.properties` principal.
- Utilizei o parâmetro `createDatabaseIfNotExist=true` na string de conexão do banco. Ao rodar a aplicação com `.\mvnw.cmd spring-boot:run`, o driver do MySQL criou automaticamente o banco de dados `estoque_autopecas` e o Hibernate gerou de forma transparente a tabela física `produto`.

### Verificação do Fluxo na Interface (CRUD)

- **Operação de Cadastro**: Acessei a página da aplicação no navegador (`http://localhost:8080/produtos`) e cliquei em "Cadastrar Nova Peça". Cadastrei novos itens de teste como *"Pastilha de Freio Bosch"* e *"Filtro de Óleo Fram"*. Validei que as restrições visuais nos campos impedem valores inválidos (como quantidade negativa e preço nulo).
- **Operação de Consulta e Filtros**: Na página principal de listagem, filmei as peças digitando a palavra *"Filtro"* na barra de pesquisa. A tabela retornou apenas a autopeça correspondente de forma instantânea.
- **Painel de Estatísticas**: Confirmei que o valor em estoque (multiplicação automática de preço por quantidade) e a contagem total de itens no topo da tela atualizaram de forma dinâmica a cada novo cadastro.
- **Operação de Alteração**: Editei a quantidade em estoque do *"Filtro de Óleo Fram"* para um número maior e notei que o indicador de alerta de estoque baixo mudou de estado imediatamente na tabela.
- **Operação de Exclusão**: Cliquei em "Excluir" em um dos itens de teste, confirmei o diálogo de alerta do navegador e verifiquei que o registro foi totalmente removido tanto da tabela da interface quanto da base de dados do MySQL.

Abaixo está o registro visual da execução das operações de validação no navegador:

![Gravação da Validação Manual](./estoque_autopecas_demo.webp)
