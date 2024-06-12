FROM openjdk:23-slim
EXPOSE 8080

ARG MAIL_USER_ARG
ARG MAIL_PASSWORD_ARG

ENV MAIL_USER=${MAIL_USER_ARG}
ENV MAIL_PASSWORD=${MAIL_PASSWORD_ARG}

COPY target/email-messages-consumer-kafka-0.0.1-SNAPSHOT.jar email-messages-consumer-kafka-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/email-messages-consumer-kafka-0.0.1-SNAPSHOT.jar"]
