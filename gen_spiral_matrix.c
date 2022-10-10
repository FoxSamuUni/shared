#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: gen <size>");
        return EXIT_FAILURE;
    }

    int size = atoi(argv[1]);
    long long ms = (long long) size * size;

    int n = 0;
    while (size > 0) {
        int sm1 = size - 1;

        if (size > 1) {
            for (int i = 0; i < 4; i ++) {
                for (int j = 0; j < sm1; j ++) {
                    if (n) {
                        putchar(' ');
                    }
                    n = 1;
                    putchar('0' + i);
                }
            }
        } else {
            if (n) {
                putchar(' ');
            }
            n = 1;
            putchar('5');
        }

        size -= 2;
    }

    putchar('\n');
    return 0;
}
