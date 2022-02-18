FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 4443
ENTRYPOINT ["java","-jar","/app.jar"]