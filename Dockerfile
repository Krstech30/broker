FROM openjdk:11

COPY target/Brokers_Project-0.0.1-SNAPSHOT.jar Brokers_Project.jar

ENTRYPOINT ["java","-jar","Brokers_Project.jar"]

Expose 8081


