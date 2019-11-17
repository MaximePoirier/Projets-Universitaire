#include <opencv2/opencv.hpp>
#include <vector>
#include <math.h>
#include <sys/time.h>

__global__ void grayscaleSobel( unsigned char * rgb, unsigned char * g,unsigned char * out, std::size_t cols, std::size_t rows ) {
  auto i = blockIdx.x * (blockDim.x-2) +threadIdx.x;
  auto j = blockIdx.y * (blockDim.y-2) +threadIdx.y;
  int h, v, res;
  extern __shared__ int s[];

  if( i < cols && j < rows ) {
    s[ threadIdx.y * blockDim.x + threadIdx.x ] = (
      307 * rgb[ 3 * ( j * cols + i ) + 0]
      + 604 * rgb[ 3 * ( j * cols + i ) + 1 ]
      + 113 * rgb[  3 * ( j * cols + i ) + 2 ]
    ) / 1024;
  }

  __syncthreads();

  if( threadIdx.y < blockDim.x-1 && threadIdx.x < blockDim.y-1  && threadIdx.x > 0 && threadIdx.y > 0) {
		// Horizontal
		h =     s[((threadIdx.y - 1) * blockDim.x + threadIdx.x - 1)] -     s[((threadIdx.y - 1) * blockDim.x + threadIdx.x + 1)]
		  + 2 * s[( threadIdx.y      * blockDim.x + threadIdx.x - 1)] - 2 * s[( threadIdx.y      * blockDim.x + threadIdx.x + 1)]
		  +     s[((threadIdx.y + 1) * blockDim.x + threadIdx.x - 1)] -     s[((threadIdx.y + 1) * blockDim.x + threadIdx.x + 1)];

		// Vertical

		v =     s[((threadIdx.y - 1) * blockDim.x + threadIdx.x - 1)] -     s[((threadIdx.y + 1) * blockDim.x + threadIdx.x - 1)]
		  + 2 * s[((threadIdx.y - 1) * blockDim.x + threadIdx.x    )] - 2 * s[((threadIdx.y + 1) * blockDim.x + threadIdx.x    )]
		  +     s[((threadIdx.y - 1) * blockDim.x + threadIdx.x + 1)] -     s[((threadIdx.y + 1) * blockDim.x + threadIdx.x + 1)];

		//h = h > 255 ? 255 : h;
		//v = v > 255 ? 255 : v;
		res = h*h + v*v;
		res = res > 255*255 ? res = 255*255 : res;

		out[(j * cols + i)] = sqrt(float(res));
	}
}


 void init(std::string const & name_file_in ,std::string const & name_file_out,int nthreadsx,int nthreadsy){
        cv::Mat m_in = cv::imread(name_file_in, cv::IMREAD_UNCHANGED );

   auto rgb = m_in.data;
   auto rows = m_in.rows;
   auto cols = m_in.cols;

   std::vector< unsigned char > g( rows * cols ); // image de sortie.
   std::vector< unsigned char > out( rows * cols );//image sortie sobel

   cv::Mat m_out( rows, cols, CV_8UC1, out.data() );

   unsigned char * rgb_d;
   unsigned char * g_d;
   unsigned char * out_d;

   cudaEvent_t start,stop;
   cudaEventCreate(&start);
   cudaEventCreate(&stop);
   cudaEventRecord(start);



   cudaMalloc( &rgb_d,3*rows * cols *sizeof(unsigned char) ); // allocation pour l'image d'entrée sur le device.
   cudaMalloc( &g_d,g.size()*sizeof(unsigned char) ); // allocation pour l'image de sortie gray sur le device.
   cudaMalloc( &out_d,out.size()*sizeof(unsigned char) ); // allocation pour l'image de sortie sobel sur le device.

   cudaMemcpy(rgb_d,rgb,3*rows * cols *sizeof(unsigned char) , cudaMemcpyHostToDevice ); // copie de l'image d'entrée vers le device.

   dim3 t( nthreadsx, nthreadsy );
   dim3 b( ( cols - 1) / (t.x-2) + 1 , ( rows - 1 ) / (t.y-2) + 1 );

   grayscaleSobel
<<< b, t, nthreadsx*nthreadsy *sizeof(int) >>>( rgb_d ,g_d , out_d ,cols,rows );

   cudaMemcpy(out.data(),out_d,out.size()*sizeof(unsigned char),cudaMemcpyDeviceToHost ); // récupération de l'image
   cudaDeviceSynchronize(); // Attente de la fin d'exécution du kernel.
   cudaError err = cudaGetLastError();
   if( err != cudaSuccess )
   {
     std::cerr << cudaGetErrorString( err ); // récupération du message associé au code erreur.
   }

   cudaEventRecord(stop);
   cudaEventSynchronize(stop);
   float elapseTime;
   cudaEventElapsedTime(&elapseTime,start,stop);
   std::cout<<elapseTime<<"ms"<<std::endl;
   cv::imwrite( name_file_out, m_out ); // sauvegarde de l'image.

   cudaFree( rgb_d );
   cudaFree( g_d);
   cudaFree(out_d);
 }

int main()
{

  init("../in.jpg","../out.jpg",32,32);

 }
