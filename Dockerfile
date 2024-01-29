FROM openjdk:11

COPY target/broker-0.0.1-SNAPSHOT.jar broker.jar

ENTRYPOINT ["java","-jar","broker.jar"]

Expose 8081


