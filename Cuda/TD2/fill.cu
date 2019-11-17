#include <iostream>
#include <vector>
#include <cuda_runtime.h>

__global__ void fill(int * v,std::size_t size){
  // Get the id of the thread (0 -> 99)
  auto tid = threadIdx.x;
  v[tid] = tid;
}

int main(){
  std::vector<int> v(100);
  int * v_d = nullptr;

  //Allocate on the Device
  cudaMalloc(&v_d,v.size()*sizeof(int));

  fill<<<1,100>>>(v_d, v.size());

  cudaMemcpy(v.data(),v_d,v.size() * sizeof(int), cudaMemcpyDeviceToHost);

  for(auto x: v){
    std::cout<< x <<" ";
  }
}
