# Controle de Projetos API

Aplicação full-stack desenvolvida com **Spring Boot, Bootstrap, MySQL e Docker**.

## Objetivo do Trabalho
O desenvolvimento desta API visa demonstrar na prática os conceitos de:
* Programação orientada a objetos.
* Criação de endpoints REST.
* Configuração de infraestrutura local utilizando Docker.
* Integração Backend e Frontend.

## Tecnologias Utilizadas (Stack)

### Backend

* Java
* Spring Boot 
* Spring Data JPA / Hibernate
* MySQL 

### Frontend

* HTML
* CSS
* Bootstrap 

### Infraestrutura 

* Docker & Docker Compose
* Maven


## Como rodar o projeto na sua máquina

### 1. Pré-requisitos
Certifique-se de ter instalado em sua máquina: 

* [Java 17+](https://www.oracle.com/java/technologies/downloads/#jdk26-linux)
* [Maven](https://maven.apache.org/download.cgi)
* [Docker e Docker Compose](https://www.docker.com/products/docker-desktop)
* [Git](https://git-scm.com/)

### 2. Clonar o repositório
```bash
git clone https://github.com/diogolsl/ControleProjetos.git
cd ControleProjetos
```

### 3. Criar ambiente 

```bash 
cp .env.example .env 
```
Edite o `.env` com seus valores.

### 4. Subir infraestrutura do Banco de Dados
```bash 
docker-compose up -d
```

Aguarde alguns segundos para o MySQL inicializar

### 5. Iniciar aplicação Spring Boot
```bash 
mvn spring-boot:run
```

### 6. Acessar API
* Acesso ao swagger (testar endpoints) : http://localhost:8080/swagger-ui/index.html
* Acesso a interface: http://localhost:8080

### 7. Como encerrar a aplicação 
* Para parar a API: Pressione Ctrl + C no terminal onde o Spring Boot está rodando.
* Para parar o Banco de Dados: Execute `docker-compose down` no terminal.

