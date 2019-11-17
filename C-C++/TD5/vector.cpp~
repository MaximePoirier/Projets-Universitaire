# include <stdlib.h>

# include "vector.hpp"


# undef NDEBUG
# include <assert.h>


using namespace std ; 


// Might prove usefull
# define COPY_VECT( v_dest , v_source )					\
  {									\
    ( v_dest ) . size = ( v_source ) . size ;				\
    element = new float [ size ] ;					\
    for ( unsigned int i = 0 ; i < size ; i++ ) {			\
      ( v_dest ) . element [ i ] = ( v_source ) . element [ i ] ;	\
    }									\
  }


Vector :: Vector ( const Vector & v ) { 
}


Vector :: ~Vector () { 
}


void Vector :: init_alea () { 
  for ( unsigned int i = 0 ; i < size ; i++ ) {	
    element[ i ] = drand48 () ; 
  }					
}


float & Vector :: operator [] ( unsigned int const i ) { 
  return (* (float *) NULL) ;
}


float const & Vector :: operator [] ( unsigned int const i ) const { 
  return (* (float *) NULL ) ;
}


std :: ostream & operator << ( std :: ostream& ost ,
			       Vector const & v ) { 
  return ost ; 
}


Vector & Vector :: operator = ( Vector const & v ) { 
  return ( * this ) ; 
}


Vector Vector :: operator + ( Vector const & v ) const { 
  return v ;
}


Vector Vector :: operator - ( Vector const & v ) const { 
  return v ;
}


Vector Vector :: operator * ( float const a ) const { 
  return *(Vector *) NULL ;
}


float Vector :: operator | ( Vector const & v ) const { 
  return 0 ;
}


bool Vector :: operator == ( Vector const & v ) const { 
  return false ;
}


Vector operator * ( float const a ,
		    Vector const & v ) { 
  return v ;
}



