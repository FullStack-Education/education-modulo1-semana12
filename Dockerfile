FROM maven:3.8.6-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/app.jar"]

EXPOSE 8080
