FROM eclipse-temurin:21-jre-jammy
LABEL maintainer="sergej-stk"
LABEL description="Mercury Spring Boot API"
ARG JAR_FILE=target/mercury-*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]