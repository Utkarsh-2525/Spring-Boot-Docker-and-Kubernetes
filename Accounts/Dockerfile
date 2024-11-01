# start with a base image containing Java Runtime
FROM openjdk:21-jdk-slim

# information of who maintains the image
LABEL "org.opencontainers.image.authors"="https://github.com/Utkarsh-2525"

# add the application's jar to image
COPY target/Accounts-0.0.1-SNAPSHOT.jar Accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "Accounts-0.0.1-SNAPSHOT.jar"]