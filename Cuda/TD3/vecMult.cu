#include <iostream>
#include <vector>
#include <cuda_runtime.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


__global__ void vecAdd(int * v0, int * v1, int * v2, std::size_t size){
  extern __shared__ int v0tmp[];
  auto tid = blockIdx.x * blockDim.x + threadIdx.x;
  if(tid<size){
    v0tmp[tid] = v0[tid];
    v2[tid] = 0;
    for(std::size_t i = 0; i<10; i++){
        v2[tid] += v0tmp[tid] + v1[tid];
    }
  }
}


int main(){
  std::size_t size = 1000;

  std::vector<int> v0(size);
  std::vector<int> v1(size);
  std::vector<int> v2(size);

  int * v0_d = nullptr;
  int * v1_d = nullptr;
  int * v2_d = nullptr;

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

  err = cudaMalloc(&v2_d,v2.size() * sizeof(int));

  cudaMemcpy(v0_d,v0.data(),v0.size() * sizeof(int),cudaMemcpyHostToDevice);
  cudaMemcpy(v1_d,v0.data(),v1.size() * sizeof(int),cudaMemcpyHostToDevice);

  dim3 block(1024);
  dim3 grid((size-1)/ block.x +1);
  vecAdd<<<grid,block, size * sizeof(int)/*octets*/>>>(v0_d,v1_d,v2_d,v0.size());

  cudaDeviceSynchronize();
  err = cudaGetLastError();
  if(err != cudaSuccess){
    std::cerr << cudaGetErrorString(err) << std::endl;
    return 1;
  }

  cudaMemcpy(v2.data(),v2_d,v2.size() * sizeof(int),cudaMemcpyDeviceToHost);

  for(std::size_t i = 0; i < v2.size(); i++){
    printf("%d\n",v2[i] );
  }

  cudaFree(v0_d);
  cudaFree(v1_d);
  cudaFree(v2_d);
}
