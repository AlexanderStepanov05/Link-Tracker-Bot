FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/scrapper.jar app.jar
EXPOSE 8080 8081
CMD ["java", "-jar", "app.jar"] 