# include <stack>
# include <stdlib.h>

# include <iostream>

# include "loader_evaluator.hpp"
# include "exparith_unary.hpp"
# include "exparith_unary.hpp"
# include "exparith_binary.hpp"
# include "exparith_variable.hpp"

# include <assert.h>

# define NDEBUG 1

using namespace std ;


string const sign_read_stop = "." ;

// help write a lot of simular cases
# define CASE_UNARY( sign , exp )		\
  else if ( sign == string_read ) {		\
    assert ( 1 <= l.size() ) ;		        \
    Expr * f = l.top() ; 			\
    l.pop() ;					\
    l.push( new exp ( f ) ) ;			\
  }


// help write a lot of simular cases
# define CASE_BINARY( sign , exp )            \
  else if(sign == string_read ){              \
    assert ( 2 <= l.size()) ;                 \
    Expr * right = l.top();                   \
    l.pop();                                  \
    Expr * left = l.top();                    \
    l.pop();                                  \
    l.push ( new exp (left, right));          \
  }



Loader_Evaluator :: Loader_Evaluator ( istream & postfixe_stream ) {
  string string_read;
  stack <Expr*>l;
  getline(postfixe_stream, string_read);
  while(string_read != sign_read_stop){
    if(string_read == ":="){
      assert ( 2 <= l.size());
      Expr * val = l.top();
      l.pop();
      Expr * var = l.top();
      Variable* v = dynamic_cast<Variable *>(var);
      l.pop();
      Expr* e = new Set(v,val);
      l.push(e);
    }
    CASE_UNARY(sign_exp , Exp)
    CASE_UNARY(sign_log , Log)
    CASE_BINARY(sign_add , Add)
    CASE_BINARY(sign_sub , Sub)
    CASE_BINARY(sign_mul, Mul)
    CASE_BINARY(sign_div, Div)
    else if(string_read == "x" || string_read == "y" || string_read == "a"){
      Expr * variable = new Variable(string_read);
      l.push(variable);
    }
    else{
      double val = atof(string_read.c_str());
      Expr * cons = new Constant(val);
      l.push(cons);
    }
    getline(postfixe_stream, string_read);
  }
  expression = l.top();
  l.pop();

}



/*!
 * This is a simple context evaluation (variables are managed correctly) with the special characteristic that if a value is not valuated, it reads the value of the variable on an input stream.
 */
class Evaluation_Context_IStream
  : public Evaluation_Context_Simple {
  istream & in ;
public :
  Evaluation_Context_IStream ( istream & in )
    : in ( in ) {}
  /*! Read from the map otherwise from the input stream \c in.
   */
  double get_value ( string const & id ) {
   if(valuation.end() != valuation.find(id)){
      return valuation.find(id)->second;
   }
   else{
     string read;
     getline(in, read);
     double val = atof(read.c_str());
     return val;
   }
  }

} ;


double Loader_Evaluator :: evaluate ( istream & in ) {
  Evaluation_Context_IStream ec ( in ) ;
  return this->expression->eval(ec);
}


double Loader_Evaluator :: evaluate ( Evaluation_Context & ec ) {
  return this->expression->eval(ec);
}
