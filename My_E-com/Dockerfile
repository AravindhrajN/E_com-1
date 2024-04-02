# Use the official OpenJDK image as a parent image
FROM openjdk:17



# Copy the WAR file from your target directory into the container
COPY target/My_E-com-0.0.1-SNAPSHOT.war  /app.war

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the WAR file
ENTRYPOINT ["java","-jar"," /app.war"]
