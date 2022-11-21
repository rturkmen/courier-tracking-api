FROM openjdk:11
VOLUME /courier-tracking-api
ARG JAR_FILE=target/courier-tracking-api.jar
ADD ${JAR_FILE} courier-tracking-api.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar","/courier-tracking-api.jar"]