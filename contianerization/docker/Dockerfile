FROM openjdk:8-alpine
VOLUME /tmp
WORKDIR /usr/src
COPY ./build/libs/kafkar-0.0.1-SNAPSHOT.jar kafkar.jar
EXPOSE 8008
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","kafkar.jar"]