
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

ENV SPRING_DATASOURCE_URL=jdbc:mysql://127.0.0.1:3306/Shoemaker
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Aravind@123
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_DATASOURCE_INITIALIZATION_MODE=always
# Tomcat listens on port 8080
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]


