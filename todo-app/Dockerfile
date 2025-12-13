# -------------------------------------------------------------------
# STAGE 1: Build de l'application avec Maven
# On utilise une image Docker contenant Maven et un JDK complet
# -------------------------------------------------------------------
FROM maven:3.9-eclipse-temurin-17 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier de définition des dépendances (pom.xml)
COPY pom.xml .

# Télécharger les dépendances Maven (optimisation du cache Docker)
# Cette étape est séparée pour ne pas retélécharger si le pom.xml ne change pas
RUN mvn dependency:go-offline -B

# Copier le reste du code source
COPY src ./src

# Compiler le projet et créer le fichier JAR exécutable
# -DskipTests permet d'accélérer le build (les tests sont censés être faits avant)
RUN mvn clean package -DskipTests


# -------------------------------------------------------------------
# STAGE 2: Création de l'image d'exécution légère
# On utilise une image JRE (Java Runtime Environment) plus petite
# -------------------------------------------------------------------
FROM eclipse-temurin:17-jre-alpine

# Définir le répertoire de travail
WORKDIR /app

# Créer un utilisateur non-root pour la sécurité
# C'est une bonne pratique pour éviter que l'app tourne en tant que root
RUN addgroup -S spring && adduser -S spring -G spring

# Copier le fichier JAR généré lors du premier stage
# On le renomme 'app.jar' pour plus de simplicité
COPY --from=build /app/target/*.jar app.jar

# Changer le propriétaire du fichier jar
RUN chown spring:spring app.jar

# Utiliser l'utilisateur non-root
USER spring

# Exposer le port sur lequel l'application Spring Boot écoute (par défaut 8080)
EXPOSE 8080

# Définir la commande de démarrage de l'application
# Des options Java courantes pour les conteneurs sont ajoutées
ENTRYPOINT ["java", "-jar", "/app/app.jar"]