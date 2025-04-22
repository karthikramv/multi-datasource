FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
# Install curl and netcat for the wait script
RUN apk add --no-cache curl netcat-openbsd

WORKDIR /app

# Create wait-for-it script
RUN echo '#!/bin/sh' > /wait-for-it.sh && \
    echo 'set -e' >> /wait-for-it.sh && \
    echo 'host="$1"' >> /wait-for-it.sh && \
    echo 'shift' >> /wait-for-it.sh && \
    echo 'cmd="$@"' >> /wait-for-it.sh && \
    echo 'until nc -z -v -w30 $host; do' >> /wait-for-it.sh && \
    echo '  echo "Waiting for database connection..."' >> /wait-for-it.sh && \
    echo '  sleep 2' >> /wait-for-it.sh && \
    echo 'done' >> /wait-for-it.sh && \
    echo 'echo "Database is up - executing command"' >> /wait-for-it.sh && \
    echo 'exec $cmd' >> /wait-for-it.sh && \
    chmod +x /wait-for-it.sh

# Copy the application JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Wait for MySQL and start the application
CMD ["/wait-for-it.sh", "mysqldb:3306", "--", "java", "-jar", "app.jar"]