# Stage 1: Build the Maven project
FROM maven:3-openjdk-17 AS build



# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Resolve Maven dependencies
RUN mvn dependency:go-offline

# Build the project
RUN mvn package -DskipTests && \
    rm -rf target/*

# Stage 2: Deploy to Tomcat
FROM tomcat:9-jdk17-openjdk-slim AS deploy

# Set working directory
WORKDIR /usr/local/tomcat/webapps

# Copy WAR file from build stage
COPY --from=build /target/My_E-com-0.0.1-SNAPSHOT.war ./ROOT.war

# Run Tomcat
CMD ["catalina.sh", "run"]
