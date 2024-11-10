FROM openjdk:21-jdk

COPY target/challenge-0.0.1-SNAPSHOT.jar challenge-api-0.0.1.jar

ENTRYPOINT [ "java", "-jar", "challenge-api-0.0.1.jar" ]