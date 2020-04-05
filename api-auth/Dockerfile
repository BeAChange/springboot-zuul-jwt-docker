FROM adoptopenjdk/openjdk13:ubi

LABEL maintainer="manju.inform@gmail.com"

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=target/api-auth-1.0.0.jar

ADD ${JAR_FILE} api-auth.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-auth.jar","--spring.config.location=classpath:/application.yml,file:/tmp/config/application.yml"]