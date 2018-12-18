FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
RUN mkdir /app
COPY target/${JAR_FILE} /app/app.jar
#set timezone
RUN echo "Asia/shanghai" > /etc/timezone;
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]