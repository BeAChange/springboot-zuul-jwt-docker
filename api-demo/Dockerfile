FROM adoptopenjdk/openjdk13:ubi

LABEL maintainer="manju.inform@gmail.com"

VOLUME /tmp

EXPOSE 8082

ARG JAR_FILE=target/api-demo-1.0.0.jar

ADD ${JAR_FILE} api-demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-demo.jar","--spring.config.location=classpath:/application.yml,file:/tmp/config/application.yml"]