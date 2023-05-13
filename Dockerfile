# Use centos base image from DockerHub
FROM centos:latest

# Install Java and Maven
RUN yum -y update && \
    yum -y install java-11-openjdk-devel && \
    yum -y install maven

# Set the current working directory inside the container
WORKDIR /app

# Copy local files to container
COPY . .

# Build the application
RUN mvn clean package

# The port number the container should expose
EXPOSE 8080

# The command to run our application
CMD ["java", "-jar", "target/my-app-1.0-SNAPSHOT.jar"]