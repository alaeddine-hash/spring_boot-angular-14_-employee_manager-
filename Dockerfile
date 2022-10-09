FROM openjdk:18

COPY target/employeemanager-0.0.1-SNAPSHOT.jar employeemanager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/employeemanager-0.0.1-SNAPSHOT.jar"]