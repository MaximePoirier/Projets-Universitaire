DIR=$(notdir $(PWD))
SRC=$(DIR).cpp
OBJ=$(DIR)


all : $(DIR)

%.o:%.cpp
	g++ -std=c++11 -c $< 

$(DIR):$(DIR).o
	g++ -o $@  $< -pthread

clean:
	rm -f *.o *~ \#*\# $(DIR)
