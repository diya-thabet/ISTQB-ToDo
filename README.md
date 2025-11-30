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

## **🧪 Tests et Assurance Qualité**

Le projet a été validé pour garantir sa robustesse, sa sécurité et sa performance en temps réel, couvrant l'ensemble de la pyramide de test :

* **Tests Unitaires:** Validation de la logique de tri client et de la manipulation d'état des composants isolés (ex: le changement de texte de la tâche).  
* **Tests d'Intégration:** Confirmation de la réussite des opérations CRUD avec Firestore et du fonctionnement correct de l'écoute en temps réel (onSnapshot).  
* **Tests E2E (Bout-en-Bout):** Simulation du parcours utilisateur complet (ajout, modification, complétion, suppression) et vérification de la réactivité UI/UX.  
* **Tests Non-Fonctionnels:** Validation de la faible latence de la synchronisation et de la conformité de l'isolation des données via les règles de sécurité Firestore.

## **🚀 Mise en Place (Pour le développement local)**

Assurez-vous d'avoir Node.js, React et les dépendances Firebase. Remplacez les variables globales par vos propres configurations Firebase et vérifiez vos règles de sécurité.

## **👤 Auteur et Contact**

* **Nom:** Dhia Thabet  
* **E-mail:** [mohamaddhia@gmail.com](mailto:mohamaddhia@gmail.com)  
* **LinkedIn:** [https://www.linkedin.com/in/dhiathabet](https://www.google.com/search?q=https://www.linkedin.com/in/dhiathabet)  
* **GitHub:** [https://github.com/diya-thabet](https://github.com/diya-thabet)
