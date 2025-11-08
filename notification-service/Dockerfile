# --- Giai đoạn 1: Build ---
# SỬA LỖI: Dùng image có cả Maven và JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Tách riêng phần tải dependency
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy code và build
COPY src ./src
RUN mvn clean package -DskipTests

# --- Giai đoạn 2: Image chạy ---
# SỬA LỖI: Dùng image JRE 21 để chạy
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy file .jar đã build
COPY --from=build /app/target/*.jar app.jar

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]