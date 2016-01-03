#Page Rank Algo

## Context ? 
	Ce travail a �t� r�alis� dans le cadre d'un TP � l'universit� d'Avignon et des Pays du Vaucluse. Le but �tant de comprendre ce que sont les hashmap et les arbres binaires de recherche, nous eumes pour projet de r�aliser un logiciel mettant en situation l'algorithme de PageRank. 
	
## Donn�es 
	Le jeu de donn�es sur lequel nous travaillons provient de HTTRACK. Vous pouvez vous-m�me cr�er votre propre jeu de donn�e gr�ce � la commande suivante : 
	
	```
	httrack http ://ceri.univ-avignon.fr -O ./ -p1 -e -%I -M1000000000
	```
	
	Nous exloitons ses r�sultats sous deux formes :
- Sous la forme d'un fichier nomm� "new.txt" listant l'ensemble des pages atteintes et, pour chaque page, � partir de quelle autre page on l'a atteinte. 
- Un fichier index contenant un ensemble de mots-clefs et la liste des pages o� se trouve chaque mot clef. 

