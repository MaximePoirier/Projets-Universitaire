#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFLEN 512

int main(int argc, char *argv[])
{
	struct addrinfo hints, *res, *cur;
	int err, fd, n;
	char *getroot = "GET / HTTP/1.0\r\nHost: %s\r\n\r\n", buf[BUFLEN];
	
	memset(&hints,0,sizeof(hints));
	hints.ai_family=AF_INET;
	hints.ai_socktype=SOCK_STREAM;
	if ((err=getaddrinfo(argv[1],argv[2],&hints,&res))) {
		fprintf(stderr,"getaddrinfo: %s\n",gai_strerror(err));
		return 1;
	}
	for (cur=res; cur!=NULL; cur=cur->ai_next) {
		if ((fd=socket(cur->ai_family, cur->ai_socktype, cur->ai_protocol))==-1) {
			perror("socket");
			continue;
		}
		if (connect(fd, cur->ai_addr, cur->ai_addrlen)==0)
			break;
		perror("connect");
	}
	if (cur==NULL) {
		fprintf(stderr,"getaddrinfo: empty list\n");
		return 1;
	}
	freeaddrinfo(res);
    sprintf(buf, getroot, argv[1]);
	write(fd, buf, strlen(buf));
	while ((n=read(fd, buf, BUFLEN))>0)
		write(STDOUT_FILENO,buf,n);
	close(fd);
	return 0;
}
