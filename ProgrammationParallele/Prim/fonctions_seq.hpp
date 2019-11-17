#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <sstream>
#include <fstream>
#include <iostream>
using namespace std;


void tab_aleatoire(int n, int mod, int* tab, int pid);
void matrice_adjacence(int n, int mod, int* mat, int pid);
void ecriture_tab(int n, int* tab, int pid);
void ecriture_graphe(const char* nom, int n, int* tab);
void ecriture_resultat(const char* nom, int n, int* tab, int* mst_construit);
