# **Gestionnaire de Tâches Temps Réel (Todo List)**

## **🌟 Présentation du Projet**

Cette application est un gestionnaire de tâches (Todo List) développé en **React** avec **Tailwind CSS** pour l'interface. Elle utilise **Firebase Firestore** pour la persistance et la synchronisation des données en **temps réel**, assurant une expérience instantanée sur toutes les sessions. Le projet est contenu dans un unique fichier (App.jsx) pour une intégration simplifiée.

## **✨ Fonctionnalités Clés & Stack Technique**

| Catégorie | Technologie | Rôle Clé |
| :---- | :---- | :---- |
| **Frontend** | React (Hooks) / Tailwind CSS | UI moderne, responsive, gestion d'état locale et tri client-side. |
| **Backend/DB** | Firebase Firestore | CRUD complet (Création, Lecture, Mise à Jour, Suppression) et synchronisation instantanée (onSnapshot). |
| **Sécurité** | Firebase Auth | Authentification par token ou anonyme et isolation stricte des données utilisateur. |
| **Architecture** | Monofichier (JSX) | Contrainte d'environnement respectée. |

## **🛠️ Architecture et Configuration Firestore**

Les données sont stockées de manière sécurisée et privée sous le chemin d'accès suivant, propre à chaque utilisateur :

/artifacts/{\_\_app\_id}/users/{userId}/todo\_items

Chaque document de tâche contient les champs : task (String), isDone (Boolean) et createdAt (Timestamp). L'application s'initialise grâce aux variables d'environnement globales : \_\_firebase\_config, \_\_app\_id, et \_\_initial\_auth\_token.

## **🧪 Tests, Assurance Qualité et Outils DevOps**

La robustesse et la qualité du projet ont été validées en utilisant des outils professionnels couvrant l'ensemble du cycle de vie du développement logiciel :

* **Tests Fonctionnels et Unitaires:** Validation de la logique métier, du tri client et du fonctionnement du flux utilisateur (Unitaires, Intégration, E2E).  
* **Gestion des Tests (Xray/JIRA):** Les cas de test et la traçabilité des exigences sont gérés via **Xray** intégré à JIRA.  
* **Tests de Performance (JMeter):** **Apache JMeter** a été utilisé pour effectuer des tests de charge et valider la scalabilité de la base de données Firestore.  
* **Intégration Continue (Jenkins):** Le pipeline de CI/CD est automatisé via **Jenkins** pour garantir la construction, le test et le déploiement rapides et fiables après chaque modification.  
* **Qualité Temps Réel:** Validation de la faible latence de la synchronisation des données et de la conformité des règles de sécurité Firestore.

## **🚀 Mise en Place (Pour le développement local)**

Assurez-vous d'avoir Node.js, React et les dépendances Firebase. Remplacez les variables globales par vos propres configurations Firebase et vérifiez vos règles de sécurité.

## **👤 Auteur et Contact**

* **Nom:** Dhia Thabet  
* **E-mail:** [mohamaddhia@gmail.com](mailto:mohamaddhia@gmail.com)  
* **LinkedIn:** [https://www.linkedin.com/in/dhiathabet](https://www.google.com/search?q=https://www.linkedin.com/in/dhiathabet)  
* **GitHub:** [https://github.com/diya-thabet](https://github.com/diya-thabet)
