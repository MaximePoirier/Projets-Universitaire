## Compilation automatique de source C
# Compilteur
CC := gcc
#options de compilation
CFLAGS := -std=c99 -Wall -Wextra -pedantic -ggdb
# Règle de compilation

all :  test_listes_int test_listes_generiques test_algues

listes_int : test_listes_int.o listes_int.o
	$(CC) $(CFLAGS) -o $@ $^

test_listes_int : listes_int
	./listes_int; diff -s test_listes_int_out_acomparer.txt test_listes_int_out.txt


listes_generiques : test_listes_generiques.o listes_generiques.o
	$(CC) $(CFLAGS) -o $@ $^

test_listes_generiques : listes_generiques
	./listes_generiques; diff -s test_listes_generiques_out_acomparer.txt test_listes_generiques_out.txt


algues : algues.o listes_generiques.o
	$(CC) $(CFLAGS) -o $@ $^


test_algues : algues
	./algues; diff -s test_algues_out_acomparer.txt test_algues_out.txt


memoire_listes_int : test_listes_int
	valgrind --leak-check=full ./listes_int


memoire_listes_generiques : test_listes_generiques
	valgrind --leak-check=full ./listes_generiques


memoire_algues : algues
	valgrind --leak-check=full ./algues


%.o: %.c
	$(CC) $(CFLAGS) -o $@ -c $<



clean:
	rm *.o

doc:
	doxygen Doxyfile


TP_NUMERO := 2
TGZ_FILES :=  *.c *.h *.txt ?akefile cr.pdf
TP_DIR := TD$(TP_NUMERO)

rendu :
	[ -e cr.pdf ] || echo > cr.pdf
	cd .. ; tar czf $(TP_DIR)/rendu.tgz $(TGZ_FILES:%=$(TP_DIR)/%)
