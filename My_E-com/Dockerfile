# Stage 1: Build the Maven project
FROM maven:3-openjdk-17 AS build



# Copy Maven project files
COPY pom.xml .
COPY src ./src



# Build the project
RUN mvn package -DskipTests

# Stage 2: Deploy to Tomcat
FROM tomcat:9-jdk17-openjdk-slim AS deploy

# Set working directory
COPY --from=build target/My_E-com-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war



