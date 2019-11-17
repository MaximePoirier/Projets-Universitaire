CXX=nvcc
CXXFLAGS=-O3
LDLIBS=`pkg-config --libs opencv`


sob3: sob3.cu
	$(CXX) $(CXXFLAGS) -o $@ $< $(LDLIBS)
	./sob3

.PHONY: clean

clean:
	rm sob3
