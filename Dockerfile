FROM maven:3.8.4-openjdk-17
ARG JAR_FILE=target/usermanagement2-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
