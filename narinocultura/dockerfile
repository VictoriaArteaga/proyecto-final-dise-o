# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom y descargar dependencias primero (mejor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar el proyecto (sin tests para despliegue rápido)
RUN mvn clean package -DskipTests

---

# Etapa 2: Runtime (imagen ligera)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto que usa Render (IMPORTANTE)
ENV PORT=8080

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]