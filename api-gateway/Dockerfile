FROM adoptopenjdk/openjdk13:ubi

LABEL maintainer="manju.inform@gmail.com"

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=target/api-gateway-1.0.0.jar

ADD ${JAR_FILE} api-gateway.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-gateway.jar","--spring.config.location=classpath:/application.yml,file:/tmp/config/application.yml"]