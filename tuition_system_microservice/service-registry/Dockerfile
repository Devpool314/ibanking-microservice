# ----- Giai đoạn 1: Build -----
# (Phần này đã đúng, giữ nguyên)
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

# ----- Giai đoạn 2: Run -----
# SỬA DÒNG NÀY:
# Đổi "eclipse-temurin:21-jre-slim" thành "eclipse-temurin:21-jre-alpine"
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# (Phần còn lại giữ nguyên)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]