#include <iostream>
#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

struct max_loc {
    int max;
    int pos;
};


void generation_aleatoire(int n, int *tab, int graine, int max) {
    srand(time(NULL) + graine);
    //srand(2*graine+10);
    for (int i = 0; i < n; i++)
        tab[i] = rand() % (max + 1);
}

struct max_loc* calcul_max(int n, int* tab) {
  struct max_loc* m = (struct max_loc*) malloc(sizeof(struct max_loc));
  m->max = tab[0];
  m->pos = 0;
  for(int i =0; i<n;i++){
    if(tab[i] > m->max){
      m->max = tab[i];
      m->pos = i;
    }
  }
  return m;

}

int main(int argc, char **argv) {
    int pid, nprocs;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &pid);
    MPI_Comm_size(MPI_COMM_WORLD, &nprocs);
    MPI_Request request;
    MPI_Status status;

    int n = atoi(argv[1]); // la taille du tableau global
    int root = atoi(argv[2]); // le processeur root

    int *tab_global;

    int nbelements = n/nprocs;
    int reste = n%nprocs;
    int nlocal;

    if(pid < reste){
      nlocal=nbelements+1;
    }else{
      nlocal = nbelements;
    }

    int *tab_local;
    tab_local = new int [nlocal];

    int *sendcounts = new int[nprocs];
    int *displs = new int[nprocs];

    if (pid == root) {
        tab_global = new int[n];
        generation_aleatoire(n, tab_global, pid, 50);
        cout << "je suis :" << pid << " et le tableau initial est ";
        for (int i = 0; i < n; i++)
            cout << tab_global[i] << " ";
        cout << endl;




   	for(int j = 0; j<nprocs; j++){
		if(j < reste){
			sendcounts[j] = nbelements + 1;
			displs[j] = j * (nbelements + 1);
		}
		else {
			sendcounts[j] = nbelements;
			displs[j] = reste * (nbelements + 1) + ((j-reste) * nbelements);
		}

    	}
	for(int i=0;i<nprocs; i ++)
		cout << sendcounts[i] << " " ;
	cout<<endl;
	for(int i=0;i<nprocs; i ++)
		cout << displs[i] << " ";
	cout<<endl;

    }
    MPI_Scatterv(tab_global,sendcounts, displs, MPI_INT,tab_local, nlocal, MPI_INT,root, MPI_COMM_WORLD);



    cout << "je suis : " << pid << " et mon tableau local est : ";
    for(int i = 0; i < nlocal; i++){
      cout << tab_local[i] << " ";
    }
    cout << endl;


    struct max_loc* m = calcul_max(nlocal,tab_local);

    int maxglob;
    MPI_Reduce(&(m->max), &maxglob, 1, MPI_INT, MPI_MAX, root, MPI_COMM_WORLD);
    if(pid==root)
      cout << "je suis : " << pid << " et le max du tableau global est : " << maxglob << endl;

    // à compléter avec la distribution du tableau et le calcul du max associé à sa position dans le tableau global

    MPI_Finalize();
    return 0;
}
