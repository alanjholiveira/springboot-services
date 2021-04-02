FROM openjdk:11-jre-slim

VOLUME /tmp

EXPOSE 8090
ADD ./target/springboot-service-gateway-0.0.1-SNAPSHOT.jar gateway-zuul-server.jar

ENTRYPOINT [ "java","-jar","/gateway-zuul-server.jar" ]