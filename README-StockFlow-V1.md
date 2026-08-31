# StockFlow

API REST para gerenciamento de estoque, desenvolvida como **projeto acadêmico e pessoal de portfólio**.

O projeto foi criado com o objetivo de aplicar, na prática, conceitos estudados ao longo da graduação em **Análise e Desenvolvimento de Sistemas (ADS)**, principalmente desenvolvimento de APIs REST, persistência de dados, validação, organização em camadas, autenticação e autorização.

> **Importante:** o StockFlow é um projeto de estudo. A V1 busca demonstrar a implementação das funcionalidades e dos conceitos propostos, e não pretende representar uma solução completa de gestão de estoque para uso empresarial em produção.

## Funcionalidades da V1

- Cadastro e consulta de empresas;
- Cadastro, consulta, atualização e remoção de produtos;
- Cadastro e consulta de estoques;
- Associação de produtos aos estoques por meio de itens de estoque;
- Movimentações de entrada e saída de quantidade dos itens de estoque;
- Validações de dados e tratamento global de exceções;
- Autenticação de usuários por e-mail e senha;
- Criptografia de senhas com BCrypt;
- Autenticação baseada em **JWT (JSON Web Token)**;
- Autorização baseada nas roles `ADMIN` e `USER`;
- Desativação de usuários por administradores, sem exclusão física do registro;
- Usuário administrador inicial criado por seed;
- Documentação da API com Swagger/OpenAPI.

## Tecnologias

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Web MVC**
- **Spring Data JPA**
- **Spring Security**
- **JWT (Auth0 java-jwt)**
- **MySQL**
- **H2** (dependência disponível para o projeto)
- **Bean Validation**
- **Lombok**
- **Springdoc OpenAPI / Swagger UI**
- **Maven Wrapper**

## Arquitetura

O backend segue uma organização em camadas, separando as principais responsabilidades da aplicação:

```text
com.guilhermeDias.StockFlow
├── controller   → endpoints HTTP da API
├── service      → regras e fluxos de negócio
├── repository   → acesso aos dados com JPA
├── entity       → entidades persistidas e regras relacionadas ao domínio
├── dto          → objetos de entrada e saída da API
├── mapper       → conversão entre DTOs e entidades
├── exception    → exceções e tratamento global de erros
└── infra        → configurações de segurança, JWT, Swagger e seed
```

### Principais relacionamentos

```text
Empresa 1 ─── N Estoque
Estoque 1 ─── N ItemEstoque
Produto 1 ─── N ItemEstoque
Empresa 1 ─── N Usuario
```

`ItemEstoque` representa a associação entre um produto e um estoque, permitindo controlar a quantidade de determinado produto em cada estoque.

## Autenticação e autorização

A V1 utiliza **Spring Security + JWT**.

O fluxo básico é:

```text
Login
  ↓
Validação de e-mail e senha
  ↓
Geração do JWT
  ↓
Requisições protegidas com:
Authorization: Bearer <token>
```

Existem duas roles:

- `USER`: acesso às funcionalidades permitidas para usuários autenticados;
- `ADMIN`: possui as permissões administrativas configuradas na aplicação.

Novos usuários cadastrados pelo endpoint público de registro recebem `USER` por padrão.

### Usuário ADMIN inicial

A aplicação possui uma seed responsável por criar o usuário administrador inicial. As credenciais utilizadas pela seed são fornecidas por variáveis de ambiente, evitando que senha e dados de acesso precisem ficar gravados no código-fonte.

## Pré-requisitos

Antes de executar o projeto, instale/configure:

- **Java 21**;
- **MySQL**;
- Git.

O projeto utiliza o Maven Wrapper, portanto não é necessário instalar o Maven manualmente.

## Configuração das variáveis de ambiente

As configurações sensíveis ficam fora do código por meio de variáveis de ambiente.

Na pasta `StockFlow-backend`, copie o arquivo de exemplo:

```bash
cp .env.example .env
```

Depois, configure os valores do seu ambiente.

### Variáveis do banco de dados

```env
DB_URL=jdbc:mysql://localhost:3306/StockFlow
DB_USERNAME=seu-usuario-do-banco
DB_PASSWORD=sua-senha-do-banco
```

### Variável do JWT

```env
JWT_SECRET=sua-chave-secreta
```

A `JWT_SECRET` deve ser uma chave forte e aleatória. **Não publique o valor real no GitHub.**

### Variáveis da seed do ADMIN

```env
ADMIN_EMAIL=seu-email-admin
ADMIN_SENHA=sua-senha-admin
ADMIN_CPF=cpf-do-admin
```

Essas variáveis são utilizadas para criar o administrador inicial quando ele ainda não existir.

> O arquivo `.env` real deve permanecer fora do versionamento. O repositório fornece apenas o `.env.example`.

## Banco de dados

Crie um banco MySQL para o projeto, por exemplo:

```sql
CREATE DATABASE StockFlow;
```

Depois, ajuste `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` de acordo com o seu ambiente.

O projeto utiliza:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Portanto, as tabelas podem ser criadas ou atualizadas automaticamente pelo JPA durante a execução.

## Como executar

Entre na pasta do backend:

```bash
cd StockFlow/StockFlow-backend
```

Carregue as variáveis de ambiente do arquivo `.env`:

```bash
set -a
source .env
set +a
```

Depois, execute:

```bash
./mvnw spring-boot:run
```

No Linux/macOS, caso o Maven Wrapper não tenha permissão de execução:

```bash
chmod +x mvnw
```

E execute novamente:

```bash
./mvnw spring-boot:run
```

No Windows, utilize:

```cmd
mvnw.cmd spring-boot:run
```

## Swagger / OpenAPI

Com a aplicação em execução, a documentação interativa pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A autenticação da API utiliza Bearer Token. Depois de realizar o login e obter o JWT, use a opção **Authorize** no Swagger para informar:

```text
Bearer <seu-token>
```

A especificação OpenAPI também está disponível em:

```text
http://localhost:8080/v3/api-docs
```

## Principais endpoints

| Recurso | Método | Endpoint | Acesso |
|---|---|---|---|
| Autenticação | POST | `/api/auth/login` | Público |
| Registro | POST | `/api/auth/register` | Público |
| Empresas | GET | `/api/empresas` | Autenticado |
| Empresas | GET | `/api/empresas/{id}` | Autenticado |
| Empresas | POST | `/api/empresas` | ADMIN |
| Empresas | DELETE | `/api/empresas/{id}` | ADMIN |
| Estoques | GET | `/api/estoques` | Autenticado |
| Estoques | GET | `/api/estoques/{id}` | Autenticado |
| Estoques | POST | `/api/estoques` | ADMIN |
| Estoques | DELETE | `/api/estoques/{id}` | ADMIN |
| Produtos | GET | `/api/produtos` | Autenticado |
| Produtos | GET | `/api/produtos/{id}` | Autenticado |
| Produtos | GET | `/api/produtos/categorias/{categoria}` | Autenticado |
| Produtos | POST | `/api/produtos` | ADMIN |
| Produtos | PUT | `/api/produtos/{id}` | Autenticado |
| Produtos | DELETE | `/api/produtos/{id}` | ADMIN |
| Itens de estoque | GET | `/api/itens-estoque` | Autenticado |
| Itens de estoque | GET | `/api/itens-estoque/{id}` | Autenticado |
| Itens de estoque | POST | `/api/itens-estoque` | Autenticado |
| Entrada | POST | `/api/itens-estoque/{id}/entrada` | Autenticado |
| Saída | POST | `/api/itens-estoque/{id}/saida` | Autenticado |
| Itens de estoque | DELETE | `/api/itens-estoque/{id}` | ADMIN |
| Usuários | GET | `/api/usuarios` | Autenticado |
| Usuários | GET | `/api/usuarios/{id}` | Autenticado |
| Desativação | PATCH | `/api/usuarios/{id}/desativar` | ADMIN |

A documentação do Swagger é a referência mais completa para parâmetros, corpos de requisição, respostas e códigos HTTP de cada endpoint.

## Tratamento de exceções e validações

A aplicação possui validações utilizando Jakarta Bean Validation e um tratamento global de exceções para padronizar as respostas de erro da API.

Entre os cenários tratados estão:

- recurso não encontrado;
- CPF, CNPJ ou e-mail já cadastrado;
- categoria inexistente;
- quantidade insuficiente para retirada de estoque;
- erros de validação de entrada.

## Dados iniciais

A aplicação contém dados iniciais para facilitar a exploração da API durante os testes acadêmicos, além da seed responsável pelo usuário administrador.

O usuário ADMIN inicial é configurado pelas variáveis `ADMIN_EMAIL`, `ADMIN_SENHA` e `ADMIN_CPF`.

## Próximos passos

Para os próximos passos do StockFlow, estão previstas as seguintes evoluções:

- Integrar o backend com um front-end;
- Containerizar backend, front-end e banco de dados utilizando Docker e Docker Compose;
- Realizar uma análise de segurança com base no OWASP Top 10, identificando e corrigindo vulnerabilidades e fragilidades de implementação;
- Implementar a funcionalidade de reativação de usuários;
- Implementar testes automatizados com JUnit, Mockito e ferramentas relacionadas;
- Aprimorar endpoints e regras de negócio envolvendo a role `ADMIN`;
- Implementar isolamento de dados entre empresas;
- Implementar Refresh Token;
- Implementar migrations do banco de dados;
- Implementar um formato de Log do sistema, melhorando a observabilidade e permitindo acompanhar o funcionamento do sistema.


## Autor

Desenvolvido por **Guilherme Dias** como projeto acadêmico, pessoal e de portfólio, com foco na aplicação prática dos conteúdos estudados na graduação em ADS.
