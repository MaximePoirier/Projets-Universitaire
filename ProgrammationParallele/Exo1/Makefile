MPICXX = mpic++
SRC= $(wildcard *.cpp)
OBJ= $(SRC:.cpp=.o)
EXEC= main
run: main
	mpirun -np 4 ./main 10 0

%.o :%.cpp
	$(MPICXX) -o $@ -c $< 

main : $(OBJ) 
	$(MPICXX) -o $@ $^

.PHONY: clean mrproper

clean:
	@rm -rf *.o

mrproper: clean
	@rm -rf $(EXEC)

