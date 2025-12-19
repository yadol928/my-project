# Этап сборки: собираем jar
FROM maven:3.9.11-eclipse-temurin-25 AS build
WORKDIR /app

# Копируем pom.xml и скачиваем зависимости (кэширование слоёв)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем весь код и собираем
COPY src ./src
RUN mvn clean package -DskipTests

# Этап запуска: только JRE (легче)
FROM eclipse-temurin:25-jre
WORKDIR /app

# Копируем собранный jar из этапа сборки
COPY --from=build /app/target/*.jar app.jar

# Порт приложения
EXPOSE 8080

# Запуск
ENTRYPOINT ["java", "-jar", "/app/app.jar"]