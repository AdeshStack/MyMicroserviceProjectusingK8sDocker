FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} apigateway.jar

ENTRYPOINT ["java","-jar","/apigateway.jar"]

EXPOSE 9090