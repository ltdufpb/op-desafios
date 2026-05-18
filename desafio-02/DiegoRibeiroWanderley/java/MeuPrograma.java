public class MeuPrograma {
  public static void main(String[] args) {
    int[] primos = new int[10000 + 1];
    for (int i = 2; i <= 10000; i++) {
      primos[i] = i;
    }

    for (int i = 2; i * i <= 10000; i++) {
      if (primos[i] != -1) {
        for (int j = i * i; j <= 10000; j += i) {
          primos[j] = -1;
        }
      }
    }

    for (int i = 2; i < 10000 - 2; i++) {
      if (primos[i] != -1) {
        System.out.println(primos[i]);
      }
    }
  }
}
