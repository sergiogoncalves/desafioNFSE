# Desafio Técnico - API de Consulta de Créditos

Este projeto é uma solução completa full-stack para consulta de créditos fiscais. Ele foi desenvolvido como parte de um desafio técnico, utilizando as melhores práticas e ferramentas modernas de backend e frontend.

---

## 🛠️ Tecnologias Utilizadas

### Backend (Java + Spring Boot)
- Java 17
- Spring Boot
- Spring Data JPA + Hibernate
- PostgreSQL (via Docker)
- Liquibase (migrations e inserts automáticos)
- Kafka + Spring Kafka (mensageria assíncrona)
- Testcontainers (para testes de integração)
- JUnit + Mockito (para testes unitários)
- Docker + Docker Compose

### Frontend (Angular)
- Angular 14
- Angular Material (para UI moderna)
- HttpClient para integração com API backend
- Docker para build e deploy do frontend

---

## 🎯 Estrutura do Monorepo

```
/desafio
├── /backend      -> API Java Spring Boot
├── /frontend     -> Interface Web Angular
├── docker-compose.yml -> Orquestra todos os serviços
```

Cada projeto possui seu próprio `Dockerfile` e pode ser iniciado de forma independente ou em conjunto.

---

## 🚀 Como Executar

### Pré-requisitos
- Docker e Docker Compose instalados
- Node.js (18+) e npm para desenvolvimento local do frontend
- Java 17 instalado localmente (caso queira rodar o backend local)

### Primeiro uso (onboarding)
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
docker system prune -a --volumes -f
docker-compose build --no-cache
docker-compose up
```

A stack irá subir:
- Backend API (`http://localhost:8080`)
- Frontend (`http://localhost:4200`)
- PostgreSQL
- Kafka + Zookeeper
- Kafka UI (`http://localhost:8081`) [opcional, se ativado]
- Zookeeper UI (`http://localhost:9000`) [opcional, se ativado]

### Rodar somente backend local
```bash
cd backend
./gradlew bootRun
```

### Rodar frontend local
```bash
cd frontend
npm install
npm start
```

---

## 💻 Funcionalidades Implementadas

### Backend
- CRUD completo de Créditos Fiscais
- Endpoint único para consulta por `numeroCredito` ou `numeroNfse`
- Publicação automática de evento em Kafka após consulta
- Integração completa com Docker e Kafka
- Testes de unidade e integração
- Flyway substituído por Liquibase

### Frontend
- Tela de consulta de créditos com Angular Material
- Consumo da API backend via HttpClient
- Visualização dos dados em tabela responsiva
- Gerenciamento automático de ambientes (local e Docker)

---

## 🔎 Modelo de Dados (Exemplo JSON de Resposta)

```json
[
  {
    "id": 1,
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 5.00,
    "valorFaturado": 30000.00,
    "valorDeducao": 5000.00,
    "baseCalculo": 25000.00
  }
]
```

---

## 📦 Testes

### Backend
- `@SpringBootTest` para testes de integração
- Testcontainers para PostgreSQL e Kafka em ambiente isolado
- JUnit + Mockito para testes unitários

### Frontend
- Testes unitários básicos com Jasmine e Karma (Angular CLI)

---

## 📋 Observações

Este projeto foi desenhado para ser:
- Totalmente Dockerizado
- Reproduzível com um único comando (`docker-compose up`)
- Clean Architecture friendly
- Escalável para ambientes reais

---

## 👨‍💻 Autor

Desenvolvido como desafio técnico por Sergio Goncalves.