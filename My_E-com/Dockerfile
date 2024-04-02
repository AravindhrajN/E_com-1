x# Use the official Tomcat image as the parent image
FROM tomcat:9-jdk17-openjdk-slim

# Copy the WAR file from your target directory to the Tomcat webapps directory and rename it to ROOT.war
COPY target/My_E-com-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
