# Projet Angular - Interface Admin et Étudiant

Ce projet est une application Angular qui gère deux interfaces distinctes : une pour les **Administrateurs** et une pour les **Étudiants**. L'architecture est conçue pour séparer clairement les fonctionnalités de chaque interface, tout en permettant de partager certaines parties du code comme les modules et les routes.

## Structure du Projet

Le projet est divisé en plusieurs modules :

- **MainModule** : Le module principal qui charge conditionnellement les modules Admin ou Étudiant.
- **AdminModule** : Contient tout le code spécifique à l'interface administrateur.
- **EtudiantModule** : Contient tout le code spécifique à l'interface étudiant.


