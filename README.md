#Page Rank Algo

## Context ? 
Ce travail a été réalisé dans le cadre d'un TP à l'université d'Avignon et des Pays du Vaucluse. Le but étant de comprendre ce que sont les hashmap et les arbres binaires de recherche, nous eumes pour projet de réaliser un logiciel mettant en situation l'algorithme de PageRank. 
	
## Données 
Le jeu de données sur lequel nous travaillons provient de HTTRACK. Vous pouvez vous-même créer votre propre jeu de donnée grâce à la commande suivante : 
	
```
httrack http ://ceri.univ-avignon.fr -O ./ -p1 -e -%I -M1000000000
```
	
Nous exloitons ses résultats sous deux formes :
- Sous la forme d'un fichier nommé "new.txt" listant l'ensemble des pages atteintes et, pour chaque page, à partir de quelle autre page on l'a atteinte. 
- Un fichier index contenant un ensemble de mots-clefs et la liste des pages où se trouve chaque mot clef. 

## Utilisation 

### Etape 0 : Eclipse et Java 
Vous aurez besoin de Java 1.8 pour lancer ce programme. (il n'a pas été testé sur d'autres version de Java). 
Un projet Eclipse est déjà existant dans les fichiers proposés. 

### Etape 1 : Clone du rep 
```
https://github.com/Herlderlord/PageRankLike.git
```
### Etape 2 : Ajout des données 
Utilisez httrack pour récupérer vos fichiers "index.txt" et "new.txt", et mettez les dans un dossier nommé "data" à la racine du projet. 

### Etape 3 : Run 
Vous n'avez plus qu'à exécuter le projet dans l'environnement qui vous intéresse. 

## Fonctionnement à multi clef. 
Pour le fonctionnement à plusieurs mots clefs, le programme fait juste une union des résultats trouvés lors de la recherche de chaque mot. Si vous trouvez deux fois la même page dans différents mots clefs, vous faites tout simplement le cumule du nombre d'apparition. 

## Calculs des scores 
Trois algorithmes sont à la disposition des codeurs.
- L'un est l'algorithme naïf qui calcule le score en fonction du nombre de degrés entrant. 
- l'autre est un algorithme pondéré qui divise le premier par le nombre de degré sortant. 
- le dernier est un algorithme récursif. Il calcule le score d'une page en fonction du score des pages ayant un lien vers celle-ci. 


