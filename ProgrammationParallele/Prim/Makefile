MPICXX = mpic++
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
EXEC= main


run: main
	mpirun -np 4 ./main 5 0 graphe.dot resultat.dot
%.o :%.cpp
	$(MPICXX) -o $@ -c $< 

main : $(OBJ) 
	$(MPICXX) -o $@ $^

.PHONY: clean mrproper

clean:
	@rm -rf *.o

mrproper: clean
	@rm -rf $(EXEC)
