FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY testApp-0.0.1-SNAPSHOT.jar testApp-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java" ]
CMD ["-jar", "testApp-0.0.1-SNAPSHOT.jar"]