FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
RUN mkdir /app
COPY target/${JAR_FILE} /app/app.jar
RUN echo "Asia/shanghai" > /etc/timezone;
ENTRYPOINT ["java","-Duser.timezone=GMT+08","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar","--spring.profiles.active=docker"]
EXPOSE 5011
