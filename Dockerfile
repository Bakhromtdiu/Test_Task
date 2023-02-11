FROM openjdk:17-jdk-slim-buster
ADD target/test-chat.jar test-chat.jar

CMD ["java","-jar","test-chat.jar"]
