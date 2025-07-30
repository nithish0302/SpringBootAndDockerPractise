# ================================
# Stage 1: Build the application (JDK 24 + Maven)
# ================================
FROM eclipse-temurin:24-jdk AS build

# Install Maven manually (since no Maven+JDK24 base image)
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /gateway

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ================================
# Stage 2: Run the application (JDK 24)
# ================================
FROM eclipse-temurin:24-jdk

# Create a non-root user
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

WORKDIR /gateway

# Copy jar from build stage and rename to app.jar
COPY --from=build /gateway/target/*.jar app.jar

# Set correct ownership
RUN chown -R appuser:appgroup /gateway

USER appuser

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "app.jar"]
