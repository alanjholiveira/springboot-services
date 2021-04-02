FROM openjdk:11-jre-slim

VOLUME /tmp

ADD ./target/springboot-service-users-0.0.1-SNAPSHOT.jar service-users.jar

ENTRYPOINT [ "java","-jar","/service-users.jar" ]