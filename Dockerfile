FROM openjdk:11-jdk-slim
COPY target/*.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "comp47660gp-0.0.1-SNAPSHOT.jar"]