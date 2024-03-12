FROM maven:3.9.6-eclipse-temurin-17-focal

WORKDIR /app

ENV APP_TARGET target
ENV APP desafio-triagil-0.0.1-SNAPSHOT.jar

COPY pom.xml ./

COPY src ./src

RUN mvn package -DskipTests

EXPOSE 8080

ENTRYPOINT exec java -jar /app/target/${APP}

#FROM maven:3.9.6-eclipse-temurin-17-focal
#VOLUME /tmp
#ARG JAVA_OPTS
#ENV JAVA_OPTS=$JAVA_OPTS
#COPY target/desafio-triagil-0.0.1-SNAPSHOT.jar desafiotriagil.jar
#EXPOSE 8080
#ENTRYPOINT exec java $JAVA_OPTS -jar desafiotriagil.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar desafiotriagil.jar
