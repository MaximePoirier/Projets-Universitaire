CXX=nvcc
LDLIBS=`pkg-config --libs opencv`


grayscale: grayscale.cu
	$(CXX) -o $@ $< $(LDLIBS)

.PHONY: clean

clean:
	rm grayscale
