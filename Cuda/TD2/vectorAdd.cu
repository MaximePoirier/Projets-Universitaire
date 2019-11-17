#include <iostream>
#include <vector>
#include <cuda_runtime.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


__global__ void vecAdd(int * v0, int * v1, std::size_t size){
  auto tid = blockIdx.x * blockDim.x + threadIdx.x;
  if(tid<size){
    v0[tid] += v1[tid];
  }
}


int main(){
  std::size_t size = 10000;

  std::vector<int> v0(size);
  std::vector<int> v1(size);

  int * v0_d = nullptr;
  int * v1_d = nullptr;

  for(std::size_t i = 0; i < v0.size(); i++){
    v0[i] = v1[i] = i;
  }

  cudaError_t err;

  err = cudaMalloc(&v0_d, v0.size() * sizeof(int));
  if(err != cudaSuccess){
    std::cerr << cudaGetErrorString(err) << std::endl;
    return 1;
  }
  err = cudaMalloc(&v1_d, v1.size() * sizeof(int));

  cudaMemcpy(v0_d,v0.data(),v0.size() * sizeof(int),cudaMemcpyHostToDevice);
  cudaMemcpy(v1_d,v0.data(),v1.size() * sizeof(int),cudaMemcpyHostToDevice);

  dim3 block(1024);
  dim3 grid((size-1)/ block.x +1);
  vecAdd<<<grid,block>>>(v0_d,v1_d,v0.size());

  cudaDeviceSynchronize();
  err = cudaGetLastError();
  if(err != cudaSuccess){
    std::cerr << cudaGetErrorString(err) << std::endl;
    return 1;
  }

  cudaMemcpy(v0.data(),v0_d,v0.size() * sizeof(int),cudaMemcpyDeviceToHost);

  for(std::size_t i = 0; i < v0.size(); i++){
    printf("%d\n",v0[i] );
  }

  cudaFree(v0_d);
  cudaFree(v1_d);
}
