#include <sys/types.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define BUFLEN 1024
#define MAXCLIENT 100

int main(int argc, char *argv[])
{
	struct addrinfo hints, *res, *cur;
	int err, fd, n, nb, max, i, j, k;
	socklen_t z=0;
	char buf[BUFLEN];
	int client[MAXCLIENT];
	char *login[MAXCLIENT];
	char *str;
	fd_set fds;
	
	
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
	nb=0;
	max=fd;
	while (1) {
		FD_ZERO(&fds);
		FD_SET(fd, &fds);
		for (i=0;i<nb;i++)
			FD_SET(client[i],&fds);
		n=select(max+1, &fds, NULL, NULL, NULL);
		if (FD_ISSET(fd, &fds)) {
			client[nb]=accept(fd, NULL, &z);
			max=client[nb]>max?client[nb]:max;
			login[nb]=NULL;
			write(client[nb], "login: ", 7);
			nb++;
		}
		for (i=0;i<nb;i++)
			if (FD_ISSET(client[i], &fds)) {
				n=read(client[i], buf, BUFLEN);
				fflush(stdout);
				if (n>0) {
					if (login[i]==NULL) {
						buf[n-1]='\0';
						fflush(stdout);
						str=(char *)malloc(n);
						memcpy(str,buf,n);
						login[i]=str;
					} else {
						fflush(stdout);
						k=strlen(login[i]);
						str=malloc(n+k+2);
						memcpy(str,login[i],k);
						str[k]=':';
						str[k+1]=' ';
						memcpy(str+k+2,buf,n);
						k=n+k+2;
						for (j=0;j<nb;j++)
							if (i!=j)
								write(client[j], str, k);
						free(str);
					}
				} else {
					close(client[i]);
					free(login[i]);
					login[i]=NULL;
					nb--;
					client[i]=client[nb];
					login[i]=login[nb];
				}
			}
	}
	return 0;
}
