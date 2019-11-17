#include <iostream>
#include <vector>


__global__ void vecadd( int * v0, int * v1, std::size_t size )
{
  auto tid = threadIdx.x;
  v0[ tid ] += v1[ tid ];
}


int main()
{
  cudaError_t err;

  std::size_t const size = 100;
  std::size_t const sizeb = size * sizeof( int );

  std::vector< int > v0( size );
  std::vector< int > v1( size );

  /*
  int * v0_h = nullptr;
  int * v1_h = nullptr;
  */

  for( std::size_t i = 0 ; i < size ; ++i )
  {
    v0[ i ] = v1[ i ] = i;
  }

  int * v0_d = nullptr;
  int * v1_d = nullptr;

  cudaHostRegister( v0.data(), sizeb, cudaHostRegisterDefault );
  cudaHostRegister( v1.data(), sizeb, cudaHostRegisterDefault );

  /*
  err = cudaMallocHost( &v0_h, sizeb );
  if( err != cudaSuccess ) { std::cerr << "Error" << std::endl; }
  err = cudaMallocHost( &v1_h, sizeb);
  if( err != cudaSuccess ) { std::cerr << "Error" << std::endl; }
  */
  /*
  for( std::size_t i = 0 ; i < size ; ++i )
  {
    v0_h[ i ] = 5;
    v1_h[ i ] = 5;
  }
  */

  err = cudaMallocHost( &v0_d, sizeb );
  if( err != cudaSuccess ) { std::cerr << "Error" << std::endl; }
  err = cudaMallocHost( &v1_d, sizeb );
  if( err != cudaSuccess ) { std::cerr << "Error" << std::endl; }

  cudaStream_t streams[ 4 ];

  for( std::size_t i = 0 ; i < 4 ; ++i )
  {
    cudaStreamCreate( &streams[ i ] );
  }
  for( std::size_t i = 0 ; i < 4 ; ++i )
  {
    err = cudaMemcpyAsync( v0_d + i*size/4, v0.data() + i*size/4, sizeb/4, cudaMemcpyHostToDevice, streams[ i ] );
    if( err != cudaSuccess ) { std::cerr << "Error 3" << std::endl; }

    err = cudaMemcpyAsync( v1_d + i*size/4, v1.data() + i*size/4, sizeb/4, cudaMemcpyHostToDevice, streams[ i ] );
    if( err != cudaSuccess ) { std::cerr << "Error 3.2" << std::endl; }
  }

  for( std::size_t i = 0 ; i < 4 ; ++i )
  {
    vecadd<<< 1, 25, 0, streams[ i ] >>>( v0_d + i*size/4, v1_d + i*size/4, size/4 );
    err = cudaGetLastError();
    if( err != cudaSuccess ) { std::cerr << "Error 3.5" << std::endl; }
  }

  for( std::size_t i = 0 ; i < 4 ; ++i )
  {
    err = cudaMemcpyAsync( v0.data() + i*size/4, v0_d + i*size/4, sizeb/4, cudaMemcpyDeviceToHost, streams[ i ] );
    if( err != cudaSuccess ) { std::cerr << "Error 4" << std::endl; }
  }
  cudaDeviceSynchronize( );

  for( std::size_t i = 0 ; i < 4 ; ++i )
  {
    cudaStreamDestroy( streams[ i ] );
  }

  for( auto x: v0 )
  {
    std::cout << x << std::endl;
  }

  return 0;
}
