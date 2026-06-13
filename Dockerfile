# Estágio 1: Build (Compilação)
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia o pom.xml e faz o download das dependências offline
# (Isso acelera builds subsequentes criando uma camada de cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila o projeto, ignorando os testes para ser mais rápido
RUN mvn clean package -DskipTests

# Estágio 2: Execução (Imagem Final)
# Usa uma imagem menor (apenas com o JRE, sem o Maven) para o ambiente de produção
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior (builder)
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando que será executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
