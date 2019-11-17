#include <iostream>
#include <vector>
#include <cuda_runtime.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


__global__ void addShared(int * v0, std::size_t size){
  extern __shared__ int v0tmp[];
  auto tid = blockIdx.x * blockDim.x + threadIdx.x;
  v0tmp[tid] = v0[tid];
  __syncthreads();
  if(tid>0 && tid<size - 1){
    v0tmp[tid] += v0tmp[tid-1] + v0tmp[tid+1];
    v0[tid] = v0tmp[tid];
  }
}


int main(int argc, char const *argv[]) {
  std::size_t size = 1024;

  std::vector<int> v0(size);
  int * v0_d = nullptr;

  for(std::size_t i = 0; i < v0.size(); i++){
    v0[i] = 1;
  }

  cudaMalloc(&v0_d, v0.size() * sizeof(int));

  cudaMemcpy(v0_d,v0.data(),v0.size() * sizeof(int),cudaMemcpyHostToDevice);

  addShared<<<1,1024, 1024 * sizeof(int) >>>(v0_d,v0.size());


  cudaDeviceSynchronize();

  cudaMemcpy(v0.data(),v0_d,v0.size() * sizeof(int),cudaMemcpyDeviceToHost);

  for(std::size_t i = 0; i < v0.size(); i++){
    printf("%d\n",v0[i] );
  }

  cudaFree(v0_d);

  return 1;
}
