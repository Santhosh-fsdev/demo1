FROM openjdk:8-jdk-alpine

COPY target/demo1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT [ "java","-jar","/app.jar" ]