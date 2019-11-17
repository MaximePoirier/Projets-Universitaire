#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;


struct val_pos {
    int val;
    int pos;
};

/*!
 * pour calculer un indice local � partir d'un indice global
 * /param n la taille totale du tableau
 * /param i_global l'indice dans le tableau complet
 * /return i_local l'indice du tableau en local
 */
int global_to_local(int n, int i_global)
{
	int pid, nprocs;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid );
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs );

	int nlocal = n / nprocs;
	int reste = n % nprocs;

	int i_local = 0;

	if(pid < reste)
		i_local = i_global%(nlocal+1);
	else
		i_local = (i_global-(nlocal+1)*reste)%nlocal;

  return i_local;
}

/*!
 * pour calculer un indice global � partir d'un indice local
 * /param n la taille totale du tableau
 * /param i_local l'indice local initial
 * /return i_global l'indice de l'�l�ment correspondant dans le tableau global
 */
int local_to_global(int n, int i_local)
{

	int pid, nprocs;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid );
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs );

	int nlocal = n / nprocs;
	int reste = n % nprocs;

	int i_global = 0;

	if(pid < reste)
		i_global = pid*(nlocal+1)+i_local;
	else
		i_global = reste*(nlocal+1)+(pid-reste)*nlocal+i_local;

  return i_global;
}

/*!
 * pour calculer le num�ro du processeur destinataire � partir d'un indice dans le tableau total
 * /param n la taille totale du tableau
 * /param i_global l'indice dans le tableau global
 * /return pid_dest le num�ro du processeur propri�taire de l'�l�ment
 */
int pid_dest(int n, int i_global)
{
	int pid, nprocs;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid );
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs );

	int pid_dest;
	int nlocal = n/nprocs;
	int reste = n%nprocs;

	if(i_global<reste*(nlocal+1))
		pid_dest = i_global/(nlocal+1);
	else
		pid_dest = reste + (i_global-reste*(nlocal+1))/nlocal;

  return pid_dest;
}

/*!
 * Petite fonction pour etre s�r de g�n�rer un tableau dont tous les �l�ments sont distincts
 * \param n la taille du tableau total
 * \param tab le tableau � construire
 * \param graine pour initialiser le processus de g�n�ration al�atoire de nombre
 */
void generation(int n, int* tab, int graine)
{
  srand(time(NULL)+graine);
  //srand(2*graine+10);
  for (int i=0; i<n; i++) {
    bool test = true;
    while (test) {
      test = false;
      tab[i] = rand()%50;
      for (int j=0; j<i; j++)
	if (tab[i]==tab[j])
	  test = true;
    }
  }
}

int main ( int argc , char **argv )
{
  int pid, nprocs;
  MPI_Init (&argc , &argv) ;
  MPI_Comm_rank(MPI_COMM_WORLD, &pid ) ;
  MPI_Comm_size (MPI_COMM_WORLD, &nprocs ) ;
  MPI_Request request;
  MPI_Status status;

  int n = atoi(argv[1]);
  int root = atoi(argv[2]);

  int* tab_global = new int[n];
  int* tab_local;


	int nlocal = n/nprocs;
	int reste = n % nprocs;
	int indiceDebut;
	if(pid < reste){
		nlocal++;
		indiceDebut = pid*nlocal;
	}else{
		indiceDebut = reste*(nlocal+1)+(pid-reste)*nlocal;
	}


  if (pid==root){
		generation(n,tab_global,pid);
		for(int i = 0; i < n ; i++)
			cout << tab_global[i] << " ";
		cout << "\n";
	}

	MPI_Bcast(tab_global,n, MPI_INT,root,MPI_COMM_WORLD);

	int nbenvoi = 0;
	int * tab_final = new int[nlocal];

	for(int i = indiceDebut; i < indiceDebut + nlocal ; i++){
		struct val_pos vp;
		vp.val = tab_global[i];
		int c = 0;
		for(int j = 0; j < n; j++){
			if(tab_global[j] < vp.val)
				c++;
		}
		vp.pos = c;
		if(vp.pos < indiceDebut || vp.pos >= indiceDebut + nlocal){

			MPI_Issend(&vp,2,MPI_2INT,pid_dest(n,vp.pos),0,MPI_COMM_WORLD,&request);
			nbenvoi++;
		}
		else{
			tab_final[global_to_local(n,vp.pos)] = vp.val;
		}
	}

	for(int i=0; i<nbenvoi;i++){
		struct val_pos vp2;
		MPI_Irecv(&vp2,2,MPI_2INT,MPI_ANY_SOURCE,0,MPI_COMM_WORLD,&request);
		MPI_Wait(&request,&status);
		tab_final[global_to_local(n,vp2.pos)] = vp2.val;
	}

	cout << "je suis " <<pid << " :" ;
	for(int i=0; i<nlocal ;i++){
		cout << tab_final[i] << " ";
	}
	cout << "\n";



  int* tab_res;
  int* sendcounts;
  int* displs;

  if (pid==root) {
    int ng, nd;
    if (pid<reste) {
      ng = nlocal;
      nd = nlocal-1;
    }
    else{
      ng = nlocal+1;
      nd = nlocal;
    }
    tab_res = new int[n];
    sendcounts = new int[nprocs];
    displs = new int[nprocs];
    int ptr = 0;
    for (int i=0; i<reste; i++) {
      sendcounts[i] = ng;
      displs[i] = ptr;
      ptr+=ng;
    }
    for (int i=reste; i<nprocs; i++){
      sendcounts[i] = nd;
      displs[i] = ptr;
      ptr+=nd;
    }
  }
  MPI_Gatherv(tab_final,nlocal,MPI_INT,tab_res,sendcounts,displs,MPI_INT,root,MPI_COMM_WORLD);

  if (pid==root) {
    cout << "les résultats et je suis : " << pid << " : ";
    for (int i=0; i<n; i++)
      cout << tab_res[i] << " ";
    cout << endl;
  }

  MPI_Finalize() ;
  return 0 ;
}
