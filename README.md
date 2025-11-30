# **Gestionnaire de Tâches Temps Réel (Todo List)**

## **🌟 Présentation du Projet**

Cette application est un gestionnaire de tâches (Todo List) développé en **React** et stylisé avec **Tailwind CSS**. Elle communique avec un backend robuste implémenté en **Java Spring Boot** qui gère la logique métier, la persistance des données et expose une API RESTful pour les opérations CRUD. Le projet frontend est contenu dans un unique fichier (App.jsx) pour une intégration simplifiée dans des environnements contraints.

## **✨ Fonctionnalités Clés & Stack Technique**

| Catégorie | Technologie | Rôle Clé |
| :---- | :---- | :---- |
| **Frontend** | React (Hooks) / Tailwind CSS | UI moderne, responsive, gestion d'état locale et tri client-side. |
| **Backend/Service** | **Java Spring Boot (REST API)** | **Service RESTful exposant les endpoints CRUD** (via HTTP). Gère la logique métier et la communication avec la base de données. |
| **Base de Données** | PostgreSQL (ou Base de Données Relationnelle/NoSQL) | Persistance sécurisée et structurée des données de tâches. |
| **Authentification** | Firebase Auth (via Custom Token) | Gestion de l'identité de l'utilisateur (userId) pour l'isolation des données côté service (ou backend). |
| **Architecture** | Monofichier (JSX) | Contrainte d'environnement respectée pour le frontend. |

## **🛠️ Architecture et Configuration**

L'architecture est basée sur une communication client-serveur standard :

1. Le frontend React (App.jsx) effectue des appels HTTP (POST, GET, PUT, DELETE) vers les endpoints de l'API Spring Boot.  
2. L'API Spring Boot gère la logique de validation, interagit avec la base de données (ex: PostgreSQL) et retourne les réponses au format JSON.

Chemin de Communication Typique (Frontend-Backend) :  
\[Client React\] \<--- API RESTful \---\> \[Spring Boot Service\] \<--- JPA/Hibernate \---\> \[Base de Données\]  
L'application frontend utilise des variables d'environnement globales (\_\_firebase\_config, \_\_app\_id, \_\_initial\_auth\_token) pour son initialisation, notamment pour établir un userId via Firebase Auth, qui serait ensuite transmis au service Spring Boot pour garantir l'isolation des tâches par utilisateur.

## **🧪 Tests, Assurance Qualité et Outils DevOps**

La validation et la livraison du projet sont assurées par un ensemble d'outils professionnels couvrant l'ensemble du cycle de vie :

* **Tests Fonctionnels et Unitaires:** Validation de la logique métier (Frontend) et des services/contrôleurs de l'API Spring Boot (Backend).  
* **Gestion des Tests (Xray/JIRA):** Les cas de test et la traçabilité des exigences sont gérés via **Xray** intégré à JIRA.  
* **Tests de Performance (JMeter):** **Apache JMeter** a été utilisé pour effectuer des tests de charge et valider la résilience et la scalabilité des **endpoints de l'API Spring Boot**.  
* **Intégration Continue (Jenkins):** Le pipeline de CI/CD est automatisé via **Jenkins** pour garantir la construction, le test et le déploiement rapides et fiables du service Spring Boot.  
* **Qualité Temps Réel:** Validation de la faible latence des requêtes API et de la conformité des règles de sécurité au niveau du service.

## **🚀 Mise en Place (Pour le développement local)**

* **Backend (Spring Boot) :** Compilez et exécutez le service Spring Boot. Assurez-vous que l'API est accessible via l'URL configurée (ex: http://localhost:8080/api/todos).  
* **Frontend (React) :** Assurez-vous d'avoir Node.js, React et les dépendances Firebase (pour l'authentification seule). Remplacez les variables globales par vos configurations si nécessaire.

## **👤 Auteur et Contact**

* **Nom:** Dhia Thabet  
* **E-mail:** [mohamaddhia@gmail.com](mailto:mohamaddhia@gmail.com)  
* **LinkedIn:** [https://www.linkedin.com/in/dhiathabet](https://www.google.com/search?q=https://www.linkedin.com/in/dhiathabet)  
* **GitHub:** [https://github.com/diya-thabet](https://github.com/diya-thabet)
