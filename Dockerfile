# Étape de construction - Build avec Maven
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /workspace/app
COPY . .

# Donne les permissions nécessaires au wrapper Maven
RUN chmod +x mvnw

# Cache les dépendances Maven (optimisation build)
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean package -DskipTests

# Étape d'exécution finale
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /workspace/app/target/*.jar app.jar

EXPOSE 8080
ENV SERVER_PORT=8080
ENV SERVER_ADDRESS=0.0.0.0
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
