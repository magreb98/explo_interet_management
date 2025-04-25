# Étape de construction - Build avec Maven
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /workspace/app
COPY . .

# Cache les dépendances Maven (optimisation build)
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean package -DskipTests

# Étape d'exécution finale
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copie l'application depuis le builder
COPY --from=builder /workspace/app/target/*.jar app.jar

# Configuration Render spécifique
EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Paramètres de santé pour Render
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
