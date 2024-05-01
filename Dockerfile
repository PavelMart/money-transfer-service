FROM openjdk:17

EXPOSE 5500

ADD ./target/server-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]