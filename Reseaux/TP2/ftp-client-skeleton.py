#!/usr/bin/python
# -*- coding: utf-8 -*-

from socket import *
from sys import argv


if __name__ == '__main__':
	if len(argv)<>2:
		print "usage: %s server " % argv[0]
		exit(1)

	server = gethostbyname(argv[1])

	s = socket()
	s.connect((server,21))
	print s.recv(4096)
	
	username = raw_input("Name (%s): "%argv[1])
	s.send("USER "+username+"\r\n")
	data = s.recv(4096)
	print(data)

	password = raw_input("Password: ")
	s.send("PASS "+password+"\r\n")
	data = s.recv(4096)
	print(data)
	
	command = ""
	while (command != "exit"):
		command = raw_input("ftp> ")
		
		if command=="exit":
			s.send("QUIT\r\n")
			data = s.recv(4096)
			print(data)
		else:
			print("command not (yet) supported")
			
	s.close()
	
