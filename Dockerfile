FROM openjdk:8-jdk-alpine

VOLUME /tmp

WORKDIR /tmp

COPY ./ /tmp

ARG JAR_FILE=target/floorplan-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} floorplan.jar

EXPOSE 8080

ENTRYPOINT ["java",  "-jar", "floorplan.jar"]