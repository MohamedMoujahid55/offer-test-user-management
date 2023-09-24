# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Create a directory for your application
RUN mkdir /app

# Copy the application JAR/WAR file into the container at /app
COPY target/technical-test-air-france-0.0.1.jar /app/app.jar

# Set the working directory to /app
WORKDIR /app

# Expose the port your application will run on
EXPOSE 9090

# Command to run your application
CMD ["java", "-jar", "app.jar"]