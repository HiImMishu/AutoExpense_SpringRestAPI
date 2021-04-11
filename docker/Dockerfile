FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring && mkdir -p /data && chown -R spring /data
USER spring:spring
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
EXPOSE 8080