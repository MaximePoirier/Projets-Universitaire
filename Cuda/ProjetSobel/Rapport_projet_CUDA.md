# Rapport projet CUDA
_Vincent Renard_ _2120165_  
_Maxime Poirier_ _2136553_

_Dans le dossier le numéro du sobel est associé au numéro du titre correspondant dans ce rapport, ainsi par exemple sob4 correspond au programme associé au sobel sur un seul kernel._

## 1 - Sobel version parallèle sur CPU
  Temps : 94 ms

## 2 - Avec image intermediaire
Temps : 25 ms

## 3 - Application grayscale - sobel
Dans cette version on execute le kernel grayscale puis le kernel sobel à la suite.

Temps : 2.8 ms 

## 4 - Grayscale - sobel sur meme kernel
* _Version naïve_ (Sans utiliser de mémoire shared) temps : 2.9 ms  
Mais image de sortie endommagée : ligne horizontales et de temps en temps des lignes verticales
Idée : passer par un espace partagé.  
* _Version avec shared_ : Dans un premier temps, l'image de sortie est cadrillée par des lignes noires entre chaques bloques de threads. La solution trouvée pour régler ce problème a été d'utiliser des ghosts en supperposant les extrémités de chaques bloques avec celles de ses voisins.  
L'image de sortie étant maintenant plus petite, il a fallut rajouter des bloques de threads à droite et en bas pour que l'image de sortie soit de même taille.  
* _Version avec shared final_ temps : 2.5 ms (+ 80% d'accélération)

Essaies de modification de la taille des grilles de chaques bloques:

16|32 = dalton effect

16|64 = dalton avec des barres plus epaisses

 
## 5 - application grayscale - sobel streams