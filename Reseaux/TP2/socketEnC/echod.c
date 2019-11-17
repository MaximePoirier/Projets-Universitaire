#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFLEN 1024

int main(int argc, char *argv[])
{
	struct addrinfo hints, *res, *cur;
	int err, fd, s, n;
	socklen_t z=0;
	char buf[BUFLEN];
	
	memset(&hints,0,sizeof(hints));
	hints.ai_family=AF_INET;
	hints.ai_socktype=SOCK_STREAM;
	hints.ai_flags=AI_PASSIVE;
	if ((err=getaddrinfo(NULL,argv[1],&hints,&res))) {
		fprintf(stderr,"getaddrinfo: %s\n",gai_strerror(err));
		exit(1);
	}
	for (cur=res; cur!=NULL; cur=cur->ai_next) {
		if ((fd=socket(cur->ai_family, cur->ai_socktype, cur->ai_protocol))==-1) {
			perror("socket");
			continue;
		}
		if (bind(fd, cur->ai_addr, cur->ai_addrlen)==0) break;
		perror("bind");
	}
	if (cur==NULL) exit(1);
	freeaddrinfo(res);
	listen(fd, 5);
	while (1) {
		s=accept(fd, NULL, &z);
		n=read(s, buf, BUFLEN);
		write(s, buf, n);
		close(s);
	}
	return 0;
}
