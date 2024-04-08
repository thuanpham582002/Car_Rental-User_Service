FROM eclipse-temurin:17
VOLUME /tmp
EXPOSE 6868
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]