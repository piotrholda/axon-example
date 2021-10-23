FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080

RUN mkdir /opt/app
COPY target/axon-example-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "/opt/app/axon-example-0.0.1-SNAPSHOT.jar"]
