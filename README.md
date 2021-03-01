# Projet7_Bibliotheque
Développement d'un nouveau système d’information de la bibliothèque d’une grande ville 

---

## I - Installation et mise en place du Projet et de sa Base de données 

---

### 1 - Mise en place du projet

---

Pour commencer, je me suis rendu sur le site : https://start.spring.io/, et j'ai ajouté les dépendances souhaitées :

![photo 1 initializer](documentation/screenshots/installation_du_projet_1.png "Initializer")

 - Spring-web qui réunit plusieurs starters, permettant de faire fonctionner comme nous le souhaitons notre microservice.
 - Thymeleaf nous permet d'accéder aux objets dans les pages HTML.
 - Lombok nous permet de nous faciliter la gestion des beans.  

Une fois générer, j'ai ajouté le dossier à mon projet puis mis le dossier en tant que module.

J'ai reproduit cette opération sur tous les microservices que je souhaitai créer :  

![photo 4 initializer](documentation/screenshots/installation_du_projet_4.png "modules")

J'ai ajouté dans mes fichiers pom.xml, les dépendances dont j'avais l'utilité en fonction de mes besoins(screen du pom de mon module interface).  

![photo 1 initializer](documentation/screenshots/installation_du_projet_5.png "Pom interface-utilisateur")

### 2 - Création de la base de données sous pgAdmin 4 - V4.19

---

Tout d'abord nous allons créer sous pgAdmin 4 (version 4.19), la base de données :

- Pour commencer, j'ai ajouté la dépendance de postgresql dans mes fichiers pom.xml utilisant la base de données.  


- Puis j'ai récupéré à partir du logiciel power architect la requête de création de la structure de ma base de données, qu'ensuite j'ai pu créer grace à l'interface pgAdmin 4. 

![photo 1 initializer](documentation/screenshots/installation_du_projet_6.png "création BDD sous pgAdmin4")
  
- Je l'ai ensuite mis au propre grace au logiciel pgModeler.  

![photo 1 modèle physique de données](documentation/screenshots/installation_du_projet_3.png "modèle physique de données")


### 3 - Modification des chemins

---

- Modifier le chemin du certificat https dans le fichier 'application.properties' du module 'interface abonné' : 

![photo 1 fichier properties abonné](documentation/screenshots/installation_du_projet_2.png "fichier properties abonné")


### 4 - Connexions aux différents utilisateurs  

---

- Pour se connecter en tant qu'employé, il faut entrer l'adresse suivante : dupont.regis@yahoo.fr  
et le mot de passe : 123  

- Pour se connecter en tant qu'abonné, il faut entrer l'adresse suivante : alexandre.lardon@yahoo.fr  
  et le mot de passe : 123456  

---

## II - Fonctionnement du projet

---  

### 1 - Fonction des différents microservices

---  

 - Chaque microservices gèrent une partie de l'application. Sur mon projet, je les ai réparti en 5 modules :
    * Module biblio-interface-utilisateur
    * Module biblio-gestion-utilisateur
    * Module biblio-authentification-utilisateur
    * Module biblio-catalogue-livres
    * Module biblio-batch
  
 1. Module biblio-interface-utilisateur :

  

### 2 - Mise en place de la communication entre les modules

---  

 - 

