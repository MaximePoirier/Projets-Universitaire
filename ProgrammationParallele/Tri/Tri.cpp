/************************************************
Pour plus d'informations sur les mutex en c++11
http://en.cppreference.com/w/cpp/thread/mutex
et en français mal traduit :
http://fr.cppreference.com/w/cpp/thread/mutex
************************************************/
#include <random>
#include <iostream>
#include <chrono>
#include <ctime>
#include <thread>

using namespace std;




// à compléter : tri fusion parallèle
void tri_fusion (float* atrier,int size){
  if(size == 1)
    return;
  thread t[2];
  int nsize = size/2;
  int decallage = nsize;
  int reste = size%2;
  for (int i = 0; i< 2; i++){
    if(i==1&&reste!=0){
      nsize++;
    }
    t[i] = thread(tri_fusion,atrier+(i*decallage),nsize);
  }
  for (int i = 0; i< 2; i++){
    t[i].join();
  }
  float * tmp = new float[size];
  for(int i=0; i<size; i++){
    tmp[i]=atrier[i];
  }
  cout<<"Tableau à moitié trié : [";
  for(int i =0; i < size; i++){
    cout << atrier[i] << " ";
  }
  cout << "]"<<endl;
  int indiceGauche=0;
  int indiceDroite=decallage;
  for(int i =0;i<size;i++){
    if(tmp[indiceGauche]<tmp[indiceDroite]){
      if(indiceGauche<=decallage-1){
        atrier[i]=tmp[indiceGauche];
        indiceGauche++;
      }
      else if(indiceDroite<=decallage+nsize-1){
        atrier[i]=tmp[indiceDroite];
        indiceDroite++;
      }

    }
    else if(tmp[indiceDroite]<tmp[indiceGauche]){
      if(indiceDroite<=decallage+nsize-1){
        atrier[i]=tmp[indiceDroite];
        indiceDroite++;
      }
      else if(indiceGauche<=decallage-1){
        atrier[i]=tmp[indiceGauche];
        indiceGauche++;
      }
    }
  }
  cout<<"Tableau trié : [";
  for(int i =0; i < size; i++){
    cout << atrier[i] << " ";
  }
  cout << "]"<<endl;
  delete [] tmp;
}

// à compléter : vérification que le tableau est bien trié
void verif(float* c1, float* c2){

}

int main(int argc, char* argv[]){
  random_device rd;
  mt19937 gen(rd());
  uniform_real_distribution<> dis(0, 256);
  if (argc < 3)
    {
      cout << "Mauvaise utilisation du programme :\n " <<
	"./Tri [taille du tableau] [nombre de threads]" << endl;
      return 1;
    }
  int size = atoi(argv[1]);
  int nthreads = atoi(argv[2]);

  float * Atrier = new float[size];

  for (int i = 0; i < size ; i++){
    Atrier[i] = dis(gen);
  }

  cout<<"Tableau non-trié : [";
  for(int i =0; i < size; i++){
    cout << Atrier[i] << " ";
  }
  cout << "]"<<endl;


  tri_fusion(Atrier,size);

  cout<<"Tableau trié : [";
  for(int i =0; i < size; i++){
    cout << Atrier[i] << " ";
  }
  cout << "]"<<endl;




  delete [] Atrier;

  return 0;
}
