#include <iostream>
#include <vector>
#include <cuda_runtime.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


__global__ void matAdd(int * m0_d, int * m1_d, std::size_t w, std::size_t h){
  auto tidx = blockIdx.x * blockDim.x + threadIdx.x;
  auto tidy = blockIdx.y * blockDim.y + threadIdx.y;
  if(tidx<w && tidy<h){
    m0_d[tidy * w + tidx] += m1_d[tidy * w + tidx];
  }
}


int main(){
  std::size_t w = 10;
  std::size_t h = 10;
  std::size_t size = w*h;

  std::vector<int> m0_h(size);
  std::vector<int> m1_h(size);

  int * m0_d = nullptr;
  int * m1_d = nullptr;

  for(std::size_t i = 0; i < size; i++){
    m0_h[i] = m1_h[i] = i;
  }

  cudaError_t err;

  err = cudaMalloc(&m0_d, m0_h.size() * sizeof(int));
  if(err != cudaSuccess){
    std::cerr << cudaGetErrorString(err) << std::endl;
    return 1;
  }
  err = cudaMalloc(&m1_d, m1_h.size() * sizeof(int));

  cudaMemcpy(m0_d,m0_h.data(),m0_h.size() * sizeof(int),cudaMemcpyHostToDevice);
  cudaMemcpy(m1_d,m1_h.data(),m1_h.size() * sizeof(int),cudaMemcpyHostToDevice);

  dim3 block(32,32);
  dim3 grid((w-1)/ block.x +1, (h-1)/ block.y +1);
  matAdd<<<grid,block>>>(m0_d,m1_d,w,h);

  cudaDeviceSynchronize();
  err = cudaGetLastError();
  if(err != cudaSuccess){
    std::cerr << cudaGetErrorString(err) << std::endl;
    return 1;
  }

  cudaMemcpy(m0_h.data(),m0_d,m0_h.size() * sizeof(int),cudaMemcpyDeviceToHost);

  for(std::size_t i = 0; i < m0_h.size(); i++){
    printf("%d\n",m0_h[i] );
  }

  cudaFree(m0_d);
  cudaFree(m1_d);
}
