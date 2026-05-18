#include <stdio.h>

int main() {
    int numeros[10001];

    for (int i = 0; i <= 10000; i++) {
        numeros[i] = 1;
    }

    numeros[0] = 0;
    numeros[1] = 0;

    for (int i = 2; i * i <= 10000; i++) {

        if (numeros[i] == 1) {

            for (int j = i * i; j <= 10000; j += i) {
                numeros[j] = 0;
            }
        }
    }

    for (int i = 2; i <= 10000; i++) {

        if (numeros[i] == 1) {
            printf("%d\n", i);
        }
    }

    return 0;
}
