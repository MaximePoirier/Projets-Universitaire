#include "fonctions_seq.hpp"

void tab_aleatoire(int n, int mod, int* tab, int pid)
{
  //  srand(time(NULL)+pid);
  srand(pid);
  for (int i=0; i<n; i++) {
    tab[i] = rand()%mod;
  }
}

void matrice_adjacence(int n, int mod, int* mat, int pid)
{
  for (int i=0; i<n*n; i++) mat[i]=0;
  for (int i=0; i<n; i++) {
    int j = n-i-1;
    int* t = (int*)malloc(j*sizeof(int));    
    tab_aleatoire(j,mod,t,pid+j);
    for (int k=i+1; k<n; k++) {
      mat[i*n+k] = t[k-i-1];
      mat[k*n+i] = t[k-i-1];
    }
    free(t);
  }
}


void ecriture_tab(int n, int* tab, int pid)
{
  cout << "je suis " << pid << " -- ";
  for (int i=0; i<n; i++)
    cout << tab[i] << " ";
  cout << endl;
}


void ecriture_graphe(const char* nom, int n, int* tab)
{
    ofstream f;

    cout << "ici : " << nom << endl;
    f.open(nom,ios::out);
    f<<"digraph G {"<<endl;
    f<<"edge [dir=none]" << endl;
    f<<"node [color=red]"<< endl;
    for (int i=0; i<n; i++) {
      for (int j=0; j<i; j++) {
	if (tab[i*n+j] != 0)
	  f<<i<<" -> "<<j<<" [label= "<<tab[i*n+j]<<" ];"<<endl;
      }
    }
    f<<"}";
    f.close();
}
/* Pour construire l'arbre couvrant
 * nom est pour construire le fichier
 * n la taille
 * tab est la matrice d'adjacence
 * mst_construit donne le prédécesseur de chaque noeud
 */
void ecriture_resultat(const char* nom, int n, int* tab, int* mst_construit)
{
  ofstream f;
  f.open(nom,ios::out);
  f<<"digraph G {"<<endl;
  f<<"edge [dir=none]" << endl;
  f<<"node [color=red]"<< endl;
  for (int i=0; i<n; i++)
    if (mst_construit[i]!=-1) {
      f<<mst_construit[i]<<" -> "<<i<<" [label= "<<tab[i*n+mst_construit[i]]<<" ];"<<endl;
    }
  f<<"}";
  f.close();
}
