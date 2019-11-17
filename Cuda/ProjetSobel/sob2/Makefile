CXX=nvcc
CXXFLAGS=-O3
LDLIBS=`pkg-config --libs opencv`


sob2: sob2.cu
	$(CXX) $(CXXFLAGS) -o $@ $< $(LDLIBS)
	./sob2

.PHONY: clean

clean:
	rm sob2
