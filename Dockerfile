
ARG JAR_FILE=target/test-chat.jar

COPY ${JAR_FILE} test-chat.jar

ENTRYPOINT ["java","-jar","test-chat.jar"]
