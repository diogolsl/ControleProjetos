# Controle de Projetos 

Aplicação full-stack desenvolvida com **Spring Boot, Bootstrap, MySQL e Docker**.
* [Controle de Projetos em produção](https://controleprojetos-production.up.railway.app)
###### Obs: O deploy foi feito por razões acadêmicas e por isso não será mantido no ar. Caso queira ver a aplicação rodando, siga o passo a passo documentado a seguir.

## Sobre
* Esse projeto visa resolver uma dor real, organizar projetos de maneira intuitiva, organizada e amigável.
* O objetivo principal é auxiliar gestores e líderes de equipes que precisam organizar rotinas sem precisar lidar com sistemas complexos.
* Criador: Diogo Lucas
* Versão: 1.0.1
* Funcionalidades Principais:
    * Gestão Completa: Cadastro, edição, exclusão e listagem de todos os projetos em uma interface.
    * Atribuição de Donos: Definição de um responsável para cada demanda.
    * Controle de Tempo: Registro da data de início para acompanhamento real dos prazos.

## Objetivo do Trabalho
O desenvolvimento desta API visa demonstrar na prática os conceitos de:
* Programação orientada a objetos.
* Criação de endpoints REST.
* Configuração de infraestrutura local utilizando Docker.
* Integração Backend e Frontend.
* Garantia de qualidade com testes automatizados e análise estática de código.
* Integração Contínua (CI) com pipelines automatizados.

## Tecnologias Utilizadas (Stack)

### Backend

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

### Qualidade de Software e CI

![JUnit 5](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-8BC34A?style=for-the-badge)
![Checkstyle](https://img.shields.io/badge/Checkstyle-E8A400?style=for-the-badge)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

### Frontend

![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)

### Infraestrutura 

![Docker](https://img.shields.io/badge/docker-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker_compose-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)

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


## Qualidade e Testes Automatizados
Executar análise estática (Linting)
```bash
mvn checkstyle:check
```

Executar testes automatizados
* Teste tem foco na camada de negócio ProjetoService
```bash
mvn test 
```

## Link de Acesso ao projeto 
* [Repositório](https://github.com/diogolsl/ControleProjetos)



