FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
RUN groupadd appuser &&  useradd -g appuser -d  /app -M appuser
RUN chown -R appuser:appuser /app
USER appuser:appuser
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
USER root
RUN chown -R appuser:appuser /app
USER appuser:appuser
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "application.jar"]