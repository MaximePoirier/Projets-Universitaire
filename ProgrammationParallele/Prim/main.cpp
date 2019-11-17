#include "fonctions_seq.hpp"

int main ( int argc , char **argv )
{
  int pid, nprocs;
  MPI_Status status;
  MPI_Init (&argc , &argv) ;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;

  int taille = atoi(argv[1]);
  int nd_racine = atoi(argv[2]);
  char* graphe_fich = argv[3];
  char* resultat_fich = argv[4];

  int* tab = NULL;
  int* mst = NULL;

  int nbligne = taille/nprocs;
  int reste = taille%nprocs;
  if(pid < reste)
    nbligne++;


  int *sendcounts = new int[nprocs];
  int *displs = new int[nprocs];

  if (pid==0) {
    tab = new int[taille*taille];
    matrice_adjacence(taille,5,tab,pid);
    ecriture_tab(taille*taille, tab, pid);
    ecriture_graphe(graphe_fich,taille,tab);

    cout << "je suis :" << pid << " et le tableau initial est ";
    for (int i = 0; i < taille*taille; i++)
        cout << tab[i] << " ";
    cout << endl;

    int decallage = 0;
    for(int i = 0; i < nprocs; i++){
      if(i < reste){
        sendcounts[i] = nbligne * taille;
        displs[i] = decallage;
        decallage += nbligne*taille;
      }
      else{
        sendcounts[i] = (nbligne-1) * taille;
        displs[i] = decallage;
        decallage += (nbligne-1)*taille;
      }
    }
    for(int i=0;i<nprocs; i ++)
  		cout << sendcounts[i] << " " ;
  	cout<<endl;
  	for(int i=0;i<nprocs; i ++)
  		cout << displs[i] << " ";
  	cout<<endl;
  }
  int* tab_local = new int [nbligne*taille];
  MPI_Scatterv(tab,sendcounts, displs, MPI_INT,tab_local, nbligne*taille, MPI_INT,0, MPI_COMM_WORLD);

  cout << "je suis : " << pid << " et mon tableau local est : ";
  for(int i = 0; i < nbligne*taille; i++){
    cout << tab_local[i] << " ";
  }
  cout << endl;


  MPI_Finalize() ;


  return 0 ;
}
