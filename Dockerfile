FROM openjdk:17-jdk-slim

WORKDIR java/proj
RUN ls -larth
RUN pwd
#RUN ./mvnw clean install

COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/app.jar"]

EXPOSE 8080
