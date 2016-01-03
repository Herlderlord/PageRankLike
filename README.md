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

