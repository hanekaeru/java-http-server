# Serveur HTTP Local - Java

## Introduction

Ce serveur a été développé par @hanekaeru et par @ThePositronCode dans le cadre d'un Travail En Autonomie en Cloud pour l'Université de La Rochelle.
Il est inspiré de codes déjà existants comme :
- https://www.programmez.com/content/protocole-http-en-java
- un code fourni en annexe par notre professeur.

## Démarrage du serveur

### - Sous Windows 💻 :

Pour démarrer le serveur, plusieurs choix s'offrent à vous :

### __Avec le Script et avec Terminal :__

- Ouvrez votre Powershell ou votre Invite de Commandes.
- Allez jusqu'au répertoire du projet.
- Entrez la commande `.\start.bat`.
- Une fois ceci fait, vous n'avez qu'à entrer, dans la fenêtre de commande, le port sur lequel vous souhaitez lancer votre serveur.
- Maintenant que votre serveur est lancé, vous n'avez qu'à vous rendre ici : `http://localhost:<port>`

### __Avec le Script mais sans le Terminal :__

- Double-cliquez sur `start.bat`.
- Entrez le port que vous souhaitez pour votre serveur.

### __Uniquement avec le Terminal :__

Si vous souhaitez utiliser uniquement les commandes du Powershell ou du CMD, vous pouvez entrer les commandes suivantes :
```bat
# Compiler le fichier Serveur.java
javac Serveur.java
# Lancer le serveur
java Serveur <port>

# Pour entrer le port, vous devez retirer les <> !
```

## - Sous Linux 🐧 :

### __Avec le Script et avec Terminal :__

- Ouvrez votre Terminal.
- Allez jusqu'au répertoire du projet.
- Entrez la commande `sh start.sh`. Vous n'avez pas besoin de droit particulier, **lors des tests, aucune utilisation de la commande `sudo` n'a été requise.**
- Une fois ceci fait, vous n'avez qu'à entrer, dans la fenêtre de commande, le port sur lequel vous souhaitez lancer votre serveur.
- Maintenant que votre serveur est lancé, vous n'avez qu'à vous rendre ici : `http://localhost:<port>`

### __Uniquement avec le Terminal :__

Si vous souhaitez utiliser uniquement les commandes du Powershell ou du CMD, vous pouvez entrer les commandes suivantes :
```bash
# Compiler le fichier Serveur.java
javac Serveur.java
# Lancer le serveur
java Serveur <port>

# Pour entrer le port, vous devez retirer les <> !
```