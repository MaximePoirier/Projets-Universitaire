#ifndef _S_NAMELIST_H_
#define _S_NAMELIST_H_

#include "strlist.h"

/* manages a name list */
typedef struct Namlist {
	STRLIST *head, *tail;       /* head and tail of name list */
	STRLIST *p;	/* user current location in name list */
	int num;	/* current number of name list entries */
	int max;	/* max number of list entries before discard */
} NAMLIST;


/* add a name to the list */
void nlput(NAMLIST *nl, char *name);

/* return a name */
/* repeatedly called, will cycle through entries */
char *nlget(NAMLIST *nl);

/* return number of names in name list */
int nlcount(NAMLIST nl);

void nlclear(NAMLIST *nl);
void nlinit(NAMLIST *nl, int max);
int nldelete(NAMLIST *nl, char *name);
int nlpresent(char name[], NAMLIST nl);
int nlmatch(char name[], NAMLIST nl);


#endif /* #ifndef _S_NAMELIST_H_ */
