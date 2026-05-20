public class Primos {
    private static boolean[] crivo;
    private static final int MAX = 10000;

    public static boolean isPrimo(int num) {
        if(num < 2) {
            return false;
        }
        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        gerarCrivo();
        for (int i = 2; i <= MAX; i++) {
            if (isPrimo(i)) {
                System.out.println(i);
            }
        }
    }

    public static void gerarCrivo() {
        crivo = new boolean[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            crivo[i] = true;
        }
        for (int i = 2; (long) i * i <= MAX; i++) {
            if (crivo[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    crivo[j] = false;
                }
            }
        }
    }
}
