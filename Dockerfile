
ARG JAR_FILE=target/lesson_50-0.0.1-SNAPSHOT.jar

ARG JAR_FILE=target/lesson_50-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} lesson_50.jar

ENTRYPOINT ["java","-jar","lesson_50.jar"]
