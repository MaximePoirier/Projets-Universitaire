# include <iostream>
# include <stdlib.h>  // drand48

# include "matrix.hpp"

# undef NDEBUG
# include <assert.h>

using namespace std ;


/*! To simplify all traversal of a matrix.
 */
# define FOR_L_C( mat , code )						\
  for ( unsigned int l = 0 ; l < ( mat ) . line_nbr ; l ++ ) {		\
    for ( unsigned int c = 0 ; c < ( mat ) . column_nbr; c ++ ) {	\
      code ;								\
    }									\
  }


Matrix :: Matrix ( unsigned int const _line_nbr ,
		   unsigned int const _column_nbr )
  : line_nbr ( _line_nbr )
  , column_nbr ( _column_nbr )
  , element ( new float [ _line_nbr * _column_nbr ] ) {
}


Matrix :: Matrix ( Matrix const & m )
  : line_nbr ( m . line_nbr )
  , column_nbr ( m . column_nbr )
  , element ( new float [ m . column_nbr * m . line_nbr ] ) {
}


Matrix :: ~ Matrix () {
  delete [] element ; // works with NULL
}


void Matrix :: init_alea () {
  for(unsigned int i=0;i<(*this).line_nbr * (*this).column_nbr;i++){
    (*this).element[i] = drand48 () ;
  }
}


void Matrix :: init ( float const * coefficients ) {
  for(unsigned int i=0;i<(*this).line_nbr * (*this).column_nbr; i++){
  (*this).element[i] = coefficients[i];
  }
}


void Matrix :: set_identity () {
  assert((*this).line_nbr == (*this).column_nbr);
  for(unsigned int i=0; i<(*this).line_nbr * (*this).column_nbr;i++){
    if(i%(*this).line_nbr +1 == 0){
	     (*this).element[i]=1;
    }
    else (*this).element[i] = 0;
  }
}


std :: ostream & operator << ( std :: ostream & ost ,
			       Matrix const & m ) {
  for(unsigned int i=0;i<m.line_nbr * m.column_nbr; i++){
    ost << m.element[i];
  }
  return ost;
}


Matrix & Matrix :: operator = ( Matrix const & m ) {
  *this=Matrix(m);
  return *this;
}


Matrix Matrix :: operator + ( Matrix const & m ) const {
  assert((*this).line_nbr * (*this).column_nbr == m.line_nbr * m.column_nbr);
  Matrix res (m);
  for(unsigned int i=0; i < m.line_nbr * m.column_nbr; i++){
      res.element[i] = (*this).element[i] + m.element[i];
  }
  return res;
}


Matrix Matrix :: operator - ( Matrix const & m ) const {
  assert((*this).line_nbr * (*this).column_nbr == m.line_nbr * m.column_nbr);
  Matrix res (m);
  for(unsigned int i=0; i < m.line_nbr * m.column_nbr; i++){
      res.element[i] = (*this).element[i] - m.element[i];
  }
  return res;
}


Matrix Matrix :: operator * ( Matrix const & m ) const {
  assert((this).column_nbr == m.line_nbr);
}


Vector Matrix :: operator * ( Vector const & v ) const {
}


Matrix Matrix :: operator * ( const float a ) const {
}


Matrix operator * ( float const a ,
		    Matrix const & m ) {
  return m * a ;
}


Matrix & Matrix :: operator += ( Matrix const & m ) {
}


Matrix & Matrix :: operator *= ( Matrix const & m ) {
}


bool Matrix :: operator == ( Matrix const & m ) const {
  return false ;
}


Vector Matrix :: extract_ligne ( unsigned int const l ,
				 unsigned int const c1 ,
				 unsigned int const c2 ) const {
  return * ( Vector * ) NULL ;
}


Vector Matrix :: extract_col ( unsigned int const l1 ,
			       unsigned int const l2 ,
			       unsigned int const c ) const {
  return * ( Vector * ) NULL ;
}


Matrix Matrix :: extract_triangular_lower_diag_one () const {
  return * ( Matrix * ) NULL ;
}


Matrix Matrix :: extract_triangular_upper_diag () const {
  return * ( Matrix * ) NULL ;
}


Matrix Matrix :: extract_diagonal () const {
  return * ( Matrix * ) NULL ;
}
