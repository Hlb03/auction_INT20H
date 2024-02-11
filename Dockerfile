FROM openjdk:17-alpine

COPY /target/auctions-auction-1.0.jar /auction/backend-app-1.0.jar

RUN mkdir -p /temp/images/

ENTRYPOINT ["java", "-jar", "/auction/backend-app-1.0.jar"]