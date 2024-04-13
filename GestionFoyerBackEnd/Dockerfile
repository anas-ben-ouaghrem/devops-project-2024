FROM openjdk:17-jdk as build
RUN mkdir -p /app
WORKDIR /app
COPY target/GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar /app/GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar"]
