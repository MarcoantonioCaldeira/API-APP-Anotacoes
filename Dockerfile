FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5050

ENTRYPOINT ["java","-jar","app.jar"]