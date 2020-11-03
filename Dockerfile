FROM openjdk:14
ADD target/InfoService.jar InfoService.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "InfoService.jar"]