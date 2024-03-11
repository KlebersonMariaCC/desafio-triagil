
FROM maven:3.9.6-eclipse-temurin-17-focal
#VOLUME /tmp
WORKDIR /usr/app

COPY . .

RUN mvn clean install -DskipTests
#ARG JAVA_OPTS
#ENV JAVA_OPTS=$JAVA_OPTS
#COPY target/desafio-triagil-0.0.1-SNAPSHOT.jar ./desafiotriagil.jar

#RUN java -Djava.security.egd=file:/dev/./urandom -jar desafiotriagil.jar
COPY target/api-0.0.1-SNAPSHOT.jar ./

#CMD [ "java","-jar", "desafio-triagil-0.0.1-SNAPSHOT.jar" ]


EXPOSE 8080 

#ENTRYPOINT [ "executable" ]
ENTRYPOINT exec java -jar desafio-triagil-0.0.1-SNAPSHOT.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar desafiotriagil.jar


