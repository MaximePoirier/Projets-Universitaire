MPICXX = mpic++
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
EXEC= main

run: main
	mpirun -np 4 ./main 10 0
%.o: %.cpp
	$(MPICXX) -o $@ -c $< 

main: $(OBJ) 
	$(MPICXX) -o $@ $^

clean:
	@rm -rf *.o

cleanall: clean
	@rm -rf $(EXEC)

