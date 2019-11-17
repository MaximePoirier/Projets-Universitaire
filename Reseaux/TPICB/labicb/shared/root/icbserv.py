#!/usr/bin/python
##
# ICB chat server
# port 7326
##
#Poirier Maxime
##
from socket import *
from select import *





# create an TCP socket instance
s=socket(AF_INET, SOCK_STREAM)
s.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
# associate the socket to local port 7326
s.bind(('0.0.0.0', 7326))
# create a waiting queue
s.listen(3)
# list of currently open sockets
socks=[s]
Login=[]
while True:
  # wait for an incoming message
  lin, lout, lex=select(socks, [], [],0.05)
  for t in lin:
    if t==s: # this is an incoming connection
      (c, addr)=s.accept()
      msg="%s enterred a group\n" % addr[0]
      print msg
      socks.append(c)
    else if t[3]=="login": # someone want to login
      msg = "Logged in.\n"
      msg=msg.encode('utf-8')
      c.send(msg)
      statue[0]="[=Statue=]"
      statue[1]="You are now in group %s\n" % t[2]
      c.send(statue)
      Login.append(t) # sauvegarde des paquets login pour enregistrer, nom, group, etc...
    else if t[0]=="b": # someone want to open speak
        
