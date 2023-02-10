Bobur aka, [27 Jan 2023 at 11:07:18]:
ARG JAR_FILE=target/lesson_50-0.0.1-SNAPSHOT.jar

ARG JAR_FILE=target/lesson_50-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} lesson_50.jar

ENTRYPOINT ["java","-jar","lesson_50.jar"]
