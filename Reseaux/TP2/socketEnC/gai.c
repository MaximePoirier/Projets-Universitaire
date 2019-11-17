#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
	struct addrinfo hints, *res, *cur;
	int err;
	char host[NI_MAXHOST];
	char serv[NI_MAXSERV];
	
	memset(&hints,0,sizeof(hints));
	hints.ai_family=AF_INET;
	hints.ai_socktype=SOCK_STREAM;
	if ((err=getaddrinfo(argv[1],argv[2],&hints,&res))) {
		fprintf(stderr,"getaddrinfo: %s\n",gai_strerror(err));
		return 1;
	}
	for (cur=res; cur!=NULL; cur=cur->ai_next) {
		if ((err=getnameinfo(cur->ai_addr, cur->ai_addrlen, host, NI_MAXHOST, serv, NI_MAXSERV, NI_NUMERICHOST|NI_NUMERICSERV))) {
			fprintf(stderr, "getnameinfo: %s\n", gai_strerror(err));
			continue;
		}
		printf("host: %s port: %s\n", host, serv);
	}
	freeaddrinfo(res);
	return 0;
}
