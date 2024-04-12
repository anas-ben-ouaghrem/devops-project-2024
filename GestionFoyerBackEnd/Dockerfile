FROM openjdk:17-jdk as build
EXPOSE 8082
RUN mkdir -p /app
WORKDIR /app
COPY target/GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar /app/GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar"]
