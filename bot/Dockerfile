FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/bot.jar app.jar
EXPOSE 8090 8091
CMD ["java", "-jar", "app.jar"] 