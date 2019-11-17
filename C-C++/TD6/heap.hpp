# ifndef __HEAP_HPP_
# define __HEAP_HPP_

/*!
 * \file
 * \brief This module provide a generic (template) heap.
 *
 * \author PASD
 * \date 2017
 */

# include <iostream>


# undef NDEBUG
# include <assert.h>



// Pre-declaration to be able to declare operator <<
template <class Element>
class Heap;


// Pre-declaration to declare friend after
template <class Element>
std :: ostream & operator <<(std :: ostream & out, Heap <Element> const & h);


/*!
 * \brief This class implements a generic heap.
 *
 * It uses a binary tree such that the value held in any node is greater (or equal) to the value in its sons.
 *
 * \pre \c Element must be comparable: operators < and <= must be defined.
 *
 * Implementation:
 * \li the tree is folded into an array.
 * \li reference / pointers are used to store elements (i.e. no copy is made)
 */
template <class Element>
class Heap {

public :

  /*! Maximal capacity of the heap. */
  const unsigned int capacity;

private :

  /*! Nature of the nodes: pointers to elements.
   * The number of elements is \c capacity. */
  typedef Element* Node;

  /*! Pointer to array of size capacity.
    The array holds the values. */
  Node* const elements;

  /*! Number of values in the heap.
   * It is always at most the capacity. */
  unsigned int nb_elem;

  /*! To compare two elements (less than).
   * \param pos_1 position of the left node.
   * \param pos_2 position of the right node.
   * \pre \c  pos_1 and \c pos_2 are legal positions.
   * \return true iff the node at \c pos_1 has a value LESSER THAN the one at node \c pos_2 ).
   */
  bool lt(unsigned int const pos_1, unsigned int const pos_2) const {
    if(pos_2 >= this->nb_elem){
      return true;
    }
    if(*(elements[pos_1]) < *(elements[pos_2])){
      return (true);
    }
    else return false;
  }

  /*! To compare two elements (less or equal).
   * \param pos_1 position of the left node.
   * \param pos_2 position of the right node.
   * \pre \c  pos_1 and \c pos_2 are legal positions.
   * \return true iff the node at \c pos_1 has a value LESSER than or EQUAL to the one at node \c pos_2) */
  bool le(unsigned int const pos_1, unsigned int const pos_2) const {
    if(pos_2 >= this->nb_elem){
      return true;
    }
    if(*(elements[pos_1]) <= *(elements[pos_2])){
      return (true);
    }
    else return false;
  }
  /*!
   * To compute the index of the left son.
   * \param i position of the node.
   * \pre \c i is a legal position
   * \post returns a legal position.
   * \return the index (in the array) of the left son of the node (indicated by index i).
   */
  unsigned int get_pos_left_son(unsigned int i) const {
    return (i+(i+1));
  };

  /*!
   * To compute the index of the left son.
   * \param i position of the node.
   * \pre \c i is a legal position
   * \post returns a legal position.
   * \return the index (in the array) of the right son of the node (indicated by index i).
   */
  unsigned int get_pos_right_son(const unsigned int i) const {
    return (i+(i+2));
  };

  /*!
   * To compute the index of the father.
   * \param i position of the node.
   * \pre \c i is a legal position.
   * \post returns a legal position.
   * \return the index (in the array) of the right son of the node (indicated by index i), except for the root (it returns 0).
   */
  unsigned int get_pos_father(const unsigned int i) const {
    if(i == 0){
      return 0;
    }
    else if(i%2 == 0) return ((i-1)/2);
    else return (i/2);
  };

  /*!
   * Exchange two elements in the array.
   * \param pos_a position of one node.
   * \param pos_b position of other node.
   * \pre \c pos_a and \c pos_b are legal positions.
   */
  void swap(const unsigned int pos_a, const unsigned int pos_b) {
    assert(NULL != this->elements[pos_a] && NULL != this->elements[pos_b]);
    Node tmp = this->elements[pos_a];
    this->elements[pos_a] = this->elements[pos_b];
    this->elements[pos_b] = tmp;
  }

  /*!
   * To check the validity of the head.
   * \return true iff the heap is correct (each father less than or equal to sons).
   * This should to be used in asserts.
   */
  bool is_valid () const;

  /*!
   * Swap the node pos down throughout the heap till consistency is restored.
   * \param pos position of the node to lower
   * \pre pos is a valid location.
   * \post The heap is valid.
   */
  void lower(unsigned pos);

  /*!
   * Swap the node pos up throughout the heap till consistency is restored.
   * \pre pos is a valid location.
   * \post The heap is valid.
   */
  void raise(unsigned pos);


public :


  //
  //  CONSTRUCTOR
  //

  /*! Build an empty heap with given capacity. */
  Heap(unsigned int _capacity)
    : capacity(_capacity)
    , elements(new Node [ _capacity ])
    , nb_elem(0)
  {
    assert(is_valid ());
  };


  //
  //  DESTRUCTOR
  //

  /*! Release the array. */
  ~Heap () {
    for(unsigned int i = 0; i < nb_elem; i++){
      delete(elements[i]);
    }
  }


  //
  //  PUBLIC METHODS
  //

  /*!
   * To test the emptyness of the heap.
   * \return true iff the heap is empty
   */
  bool is_empty () const {
    if(0 == this->nb_elem){
      return (true);
    }
    else return false;
  }

  /*!
   * Remove and return the root of the heap.
   * The heap is re equilibrated by putting the last element at the root and lowering it.
   * \pre The heap is valid.
   * \post The heap is valid.
   * \return the minimum of the heap.
   */
  Element & pop();

  /*!
   * Add a value at the bottom of the tree (first empty cell) and swap it up (raise).
   * \param v value to add.
   * \pre The heap is valid.
   * \post The heap is valid.
   */
  void push(Element & v);


  //
  //  FRIENDS
  //

  friend std :: ostream & operator << <Element>(std :: ostream &, Heap const &);

};


//
// TEMPLATE
// => METHODS MUST BE HERE
//


template <class Element>
bool Heap <Element> :: is_valid () const {
  unsigned int i = 0;
  while(this->elements[i] != NULL){
    if(this->lt(i,get_pos_father(i))){
      return false;
    }
    i++;
  }
  return true;
}


template <class Element>
void Heap <Element> :: lower(unsigned int pos) {
  if(NULL == this->elements[get_pos_left_son(pos)] && NULL == this->elements[get_pos_left_son(pos)]){
    return;
  }
  if(this->le(pos,get_pos_left_son(pos)) && this->le(pos,get_pos_right_son(pos))){
    return;
  }
  if(this->le(pos,get_pos_left_son(pos)) && NULL == this->elements[get_pos_right_son(pos)]){
    return;
  }
  if(this->lt(get_pos_left_son(pos),pos) && NULL == this->elements[get_pos_right_son(pos)]){
    this->swap(pos,get_pos_left_son(pos));
    return;
  }
  if(this->lt(get_pos_right_son(pos),get_pos_left_son(pos))){
    this->swap(pos,get_pos_right_son(pos));
    lower(get_pos_right_son(pos));
  }
  else{
    this->swap(pos,get_pos_left_son(pos));
    lower(get_pos_left_son(pos));
  }
}


template <class Element>
void Heap <Element> :: push(Element & v) {
  /*assert(this->is_valid());*/
  unsigned int i = 0;
  while(this->elements[i] != NULL){
    i++;
  }
  this->elements[i] = &v;
  this->nb_elem +=1;
  this->raise(i);
  /*assert(this->is_valid());*/
}


template <class Element>
void Heap <Element> :: raise(unsigned int pos) {
  if(pos == get_pos_father(pos)){
    return;
  }
  unsigned int tmp = get_pos_father(pos);
  if(this->le(tmp,pos)){
    return;
  }
  this->swap(pos,get_pos_father(pos));
  raise(get_pos_father(pos));
}

template <class Element>
Element & Heap <Element> :: pop() {
  /*assert(this->is_valid());*/
  Element* min = this->elements[0];
  unsigned int i = 0;
  while(this->elements[i] != NULL){
    i++;
  }
  i--;
  this->elements[0] = this->elements[i];
  this->elements[i] = NULL;
  this->nb_elem -= 1;
  this->lower(0);
  /*assert(this->is_valid());*/
  return *min;
}


/*! Print the heap on the \c ostream as an array with the format:
 * \verbatim [ e0 , e1 , ... , en ] \endverbatim
 * \param out \c ostream to output to.
 * \param h Heap to output
 * \return the ostream
 */
template <class Element> std :: ostream & operator <<(std :: ostream & out, Heap<Element> const & h) {
  unsigned int i = 1;
  out << '['<<' '<< *(h.elements[0]) ;
  while(h.elements[i] != NULL){
    out << ' '<< ',' <<' '<< (*h.elements[i]) ;
    i++;
  }
  out << ' '<<']' ;
  return out;
}


# endif
