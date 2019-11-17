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
  Vector ( v.size);
  COPY_VECT( *this, v);
}


Vector :: ~Vector () {
  delete [] element;
}


void Vector :: init_alea () {
  for ( unsigned int i = 0 ; i < size ; i++ ) {
    element[ i ] = drand48 () ;
  }
}


float & Vector :: operator [] ( unsigned int const i ) {
  assert(i<=size);
  return element[i];
}


float const & Vector :: operator [] ( unsigned int const i ) const {
  assert(i<=size);
  return element[i];
}


std :: ostream & operator << ( std :: ostream& ost ,
			       Vector const & v ) {
  ost << '(';
  for(unsigned int i=0; i<v.size-1 ; i++){
    ost << v.element[i] << ',';
  }
  ost << v.element[v.size-1];
  ost<< ')' << endl;
  return ost ;
}


Vector & Vector :: operator = ( Vector const & v ) {
  * this = Vector(v);
  return ( * this ) ;
}


Vector Vector :: operator + ( Vector const & v ) const {
  assert((*this).size == v.size);
  Vector res (v.size);
  for(unsigned int i=0;i<v.size;i++){
    res.element[i] = (*this).element[i] + v.element[i];
  }
  return res ;
}


Vector Vector :: operator - ( Vector const & v ) const {
  assert((*this).size == v.size);
  Vector res (v.size);
  for (unsigned int i=0;i<v.size;i++){
    res.element[i] = (*this).element[i] - v.element[i];
  }
  return res;
}


Vector Vector :: operator * ( float const a ) const {
  Vector res ((*this).size);
  for (unsigned int i=0;i<(*this).size;i++){
    res.element[i] = (*this).element[i] * a;
  }
  return res;
}


float Vector :: operator | ( Vector const & v ) const {
  assert((*this).size == v.size);
  float sum=0;
  for(unsigned int i=0;i<v.size;i++){
        sum += (*this).element[i] * v.element[i];
  }
  return sum;
}


bool Vector :: operator == ( Vector const & v ) const {
  assert((*this).size == v.size);
  for(unsigned int i=0;i<v.size;i++){
    if((*this).element[i] != v.element[i]){
	  return false;
    }
  }
  return true;
}


Vector operator * ( float const a ,
		    Vector const & v ) {
  Vector res (v.size);
  for(unsigned int i=0;i<v.size;i++){
    res.element[i] = v.element[i] * a;
  }
  return res;
}
