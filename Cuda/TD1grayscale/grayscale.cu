#include <opencv2/opencv.hpp>
#include <vector>
#include <iostream>

__global__ void grayscale( unsigned char * rgb, unsigned char * g, std::size_t cols, std::size_t rows ) {
  auto i = blockIdx.x*blockDim.x+ threadIdx.x;
  auto j = blockIdx.y*blockDim.y+ threadIdx.y;
  if( i < cols && j < rows ) {
    g[ j * cols + i ] = (
			 307 * rgb[ 3 * ( j * cols + i ) ]
		       + 604 * rgb[ 3 * ( j * cols + i ) + 1 ]
		       + 113 * rgb[  3 * ( j * cols + i ) + 2 ]
		       ) / 1024;
  }
}

int main()
{
  cv::Mat m_in = cv::imread("in.jpg", cv::IMREAD_UNCHANGED );

  auto rgb = m_in.data;

  auto rows = m_in.rows;
  auto cols = m_in.cols;

  std::vector< unsigned char > g( rows * cols ); // image de sortie.

  cv::Mat m_out( rows, cols, CV_8UC1, g.data() );

  unsigned char * rgb_d;
  unsigned char * g_d;
  cudaEvent_t start,stop;
  cudaEventCreate(&start);
  cudaEventCreate(&stop);
  cudaEventRecord(start);

  cudaMalloc(&rgb_d,3*rows*cols); // allocation pour l'image d'entrée sur le device.
  cudaMalloc(&g_d,rows*cols); // allocation pour l'image de sortie sur le device.

  cudaMemcpy(rgb_d,rgb,3*rows*cols,cudaMemcpyHostToDevice); // copie de l'image d'entrée vers le device.

  dim3 t( 32, 32 );
  dim3 b( ( cols - 1) / t.x + 1 , ( rows - 1 ) / t.y + 1 );
  grayscale<<< b, t >>>( rgb_d,g_d,cols,rows );

  cudaMemcpy(g.data(),g_d,rows*cols, cudaMemcpyDeviceToHost); // récupération de l'image en niveaux de gris sur l'hôte.

  cudaEventRecord(stop);
  cudaEventSynchronize(stop);
  float elapseTime;
  cudaEventElapsedTime(&elapseTime,start,stop);
  std::cout<<elapseTime<<"ms"<<std::endl;
  
  cv::imwrite( "out.jpg", m_out ); // sauvegarde de l'image.
  
  cudaFree( rgb_d );
  cudaFree( g_d);

  return 0;
}
