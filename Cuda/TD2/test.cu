#include <vector>
#include <iostream>

__global__ void fill(float * a0,std::size_t size){
  auto tid = threadIdx.x;
  if(tid < size){
      a0[tid] = 1.0f;
  }
}


int main(){

  float *a0_d = nullptr;

  std::size_t const size =10000000;
  std::vector<float> a0_h(10);

  cudaMalloc(&a0_d,size*sizeof(float));

  dim3 block(32);
  dim3 grid((size-1)/block.x + 1);

  fill<<<1,size>>>(a0_d,size);

  cudaMemcpy(a0_h.data(),a0_d,size*sizeof(float), cudaMemcpyDeviceToHost);

  cudaFree(a0_d);

  for(auto v: a0_h){
    std::cout << v << std::endl;
  }

  return 0;
}
