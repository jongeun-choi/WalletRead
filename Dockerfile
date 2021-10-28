FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY /target/walletread-0.0.1-SNAPSHOT.jar walletread.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "walletread.jar"]