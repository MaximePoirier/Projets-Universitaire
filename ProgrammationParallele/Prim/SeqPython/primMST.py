from random import *
from time import *
import argparse

SOURCE=0
DEST=1
WEIGHT=2
NEIGHBORS=3
STATE=4
ACTUALWEIGHT=5
DATA_PREFIX='../data/'
INFINI=100
INMST=200

########################################
# Second version with SDD in n+k*m
# where n is the number of vertices
# and m the number of edges
########################################


def readGraphDot(vertices,edges,filename):
	fic=open(filename,"r")
	nb_edge=0
	source=''
	dest=''
	weight=''
	# reading the file
	for ligne in fic:
		l=ligne.split()
		if (len(l)!=6):
			continue
		[source_c,_,dest_c, _, weight,_]=l
		source=int(source_c)
		dest=int(dest_c)
		#adding the new edge
		if source!=dest:
			edges.append([source,dest,int(weight)])
			# This edge is added to the list of the edges having source has vertice
			if source in vertices:
				vertices[source].append(nb_edge)
			else:
				vertices[source]=[nb_edge]
			# This edge is added to the list of the edges having dest has vertice
			if dest in vertices:
				vertices[dest].append(nb_edge)
			else:
				vertices[dest]=[nb_edge]
			nb_edge+=1
		#endif
	#endfor
		
	fic.close()
	return nb_edge

def updateDist(vertices,edges,root,dist,prec):

	for numedge in vertices[root]:
		[source,dest,weight]=edges[numedge]
		if (source!=root):
			if (dist[source]!=200 and dist[source]>weight):
				dist[source]=weight
				prec[source]=(root,weight)
		else:
			if (dist[dest]!=200 and dist[dest]>weight):
				dist[dest]=weight
				prec[dest]=(root,weight)
	dist[root]=INMST

# displays the result
def displayResult(dist,prec):
	total=0
	print('digraph G {')
	print('edge [dir=none]')
	print('node [color=red]')
	for vertice in range(len(prec)):
		if prec[vertice]!="":
			(source,weight)=prec[vertice]
			print(source,' -> ',vertice,' [label= ',weight,' ];')
			total+=weight
	
	print('}')
	return total


#Prim algorithm for Minimum Spanning Tree (MST)
def prim(filename,root):
	vertices ={}
	edges = []
	readGraphDot(vertices,edges,filename)

	# updating the edges
	num_vertices=len(vertices)
	dist=[INFINI]*num_vertices
	prec=[""]*num_vertices
	updateDist(vertices,edges,root,dist,prec)
	for nbrepeat in range (num_vertices-1):
		mindist=min(dist)
		root=dist.index(mindist)
		updateDist(vertices,edges,root,dist,prec)
		
	displayResult(dist,prec)	


parser = argparse.ArgumentParser(description='Calcul du MST d\'un graphe par l\'algo de PRIM')
			
parser.add_argument('ficGraph',type=str,
                   help='nom du fichier dot contenant le graphe')

parser.add_argument('root', type=int,
                   help='sommet root du MST')

args = parser.parse_args()
prim(args.ficGraph,args.root)
