# include <sstream>

# include "exparith.hpp"


# include <assert.h>
# define NDEBUG 1


using namespace std ;



double Constant :: eval ( Evaluation_Context & ec ) const{
  return this->value;
}


string Constant :: toString () const {
  ostringstream oss;
  oss << this->value;
  string s = oss.str();
  return s ;
}
