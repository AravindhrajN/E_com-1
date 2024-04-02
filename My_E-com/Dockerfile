
# Stage 1: Build the Maven project
FROM maven:3-openjdk-17 AS build



# Copy Maven project files to the container
COPY pom.xml . 
COPY src /src

# Build the project and skip tests to speed up the build
RUN mvn package -DskipTests

# Stage 2: Deploy to Tomcat
FROM tomcat:10-jdk17-openjdk-buster AS deploy

# Copy the WAR file from the build stage to the Tomcat webapps directory
COPY --from=build target/My_E-com-0.0.1-SNAPSHOT.war.original /usr/local/tomcat/webapps/ROOT.war

# Optional: If you need to customize Tomcat server configurations, you can COPY them here
# COPY path/to/local/server.xml /usr/local/tomcat/conf/

# Tomcat listens on port 8080
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]


