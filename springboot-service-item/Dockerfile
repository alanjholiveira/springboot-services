FROM openjdk:11-jre-slim

VOLUME /tmp

EXPOSE 8002

ADD ./target/springboot-service-item-0.0.1-SNAPSHOT.jar service-items.jar

ENTRYPOINT [ "java","-jar","/service-items.jar" ]