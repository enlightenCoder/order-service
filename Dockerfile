# Use the correct Java 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /opt

# Copy the JAR file to the container
COPY target/*.jar /opt/app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
