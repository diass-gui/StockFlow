# StockFlow — API REST de Gerenciamento de Estoque

API REST para gerenciamento de produtos e estoques por empresa, desenvolvida como projeto pessoal de portfólio.

## Tecnologias

- Java 21 + Spring Boot 4.0.6
- Spring Data JPA + H2 (dev) / MySQL (prod)
- Lombok, Bean Validation
- Springdoc OpenAPI (Swagger UI)

## Modelagem

```
Empresa (1) ──── (1) Estoque (1) ──── (N) Produto
```

## Endpoints

| Recurso | Base URL |
|---------|----------|
| Empresa | `/api/empresas` |
| Estoque | `/api/estoques` |
| Produto | `/api/produtos` |

Todos os recursos suportam `GET`, `POST` e `DELETE`. Produto também suporta `PUT`. Acesse o Swagger para detalhes completos.

## Como Executar

```bash
git clone https://github.com/diass-gui/StockFlow.git
cd StockFlow/StockFlow-backend
./mvnw spring-boot:run
```

| Interface | URL |
|-----------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| H2 Console | `http://localhost:8080/h2-console` |

## Autor

Desenvolvido por **Guilherme Dias** — projeto pessoal de portfólio e fixação de conteúdos da graduação em ADS.