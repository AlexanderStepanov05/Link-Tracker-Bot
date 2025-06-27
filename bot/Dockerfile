FROM eclipse-temurin:17-jre
WORKDIR /app
COPY bot/target/bot.jar app.jar
EXPOSE 8090 8091
CMD ["java", "-jar", "app.jar"] 