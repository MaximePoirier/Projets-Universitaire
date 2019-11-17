CXX=nvcc
CXXFLAGS=-O3
LDLIBS=`pkg-config --libs opencv`


sob4: sob4.cu
	$(CXX) $(CXXFLAGS) -o $@ $< $(LDLIBS)
	./sob4

.PHONY: clean

clean:
	rm sob4
