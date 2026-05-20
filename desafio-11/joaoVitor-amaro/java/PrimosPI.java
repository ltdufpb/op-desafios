import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrimosPI {
    private static boolean[] crivo;
    private static final int MAX = 10000;
    public static void main(String[] args) {
        gerarCrivo();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linha = br.readLine();
            linha = linha.substring(2);
            String melhorSequencia = "";
            int i = 0;
            while (i < linha.length()) {
                StringBuilder sequenciaAtual = new StringBuilder();
                int pos = i;
                while (pos < linha.length()) {
                    boolean encontrou = false;
                    for (int tam = 4; tam >= 1; tam--) {
                        if (pos + tam > linha.length()) {
                            continue;
                        }
                        String pedaco = linha.substring(pos, pos + tam);
                        int numero = Integer.parseInt(pedaco);
                        if (isPrimo(numero)) {
                            sequenciaAtual.append(pedaco).append(" ");
                            pos += tam;
                            encontrou = true;
                            break;
                        }
                    }
                    if (!encontrou) {
                        break;
                    }
                }
                String seq = sequenciaAtual.toString().trim();
                int digitosSeq = seq.replace(" ", "").length();
                int digitosMelhor = melhorSequencia.replace(" ", "").length();

                if (digitosSeq > digitosMelhor) {
                    melhorSequencia = seq;
                }

                i++;
            }
            System.out.println(melhorSequencia.replace(" ", ""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean isPrimo(int num) {
        if (num < 2 || num > MAX) {
            return false;
        };
            return crivo[num];
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
