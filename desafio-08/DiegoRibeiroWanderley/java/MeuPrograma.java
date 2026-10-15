import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MeuPrograma {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String linha = br.readLine();

            while (linha != null) {
                String fracao = linha;

                if (!fracao.contains("/")) {
                    fracao = fracao + "/1";
                }

                String[] fracaoVetor = fracao.replace(" ", "").split("/");

                int numerador = Integer.parseInt(fracaoVetor[0]);
                int denominador = Integer.parseInt(fracaoVetor[1]);

                System.out.println(montarFracao(numerador, denominador));

                linha = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String montarFracao(Integer numerador, Integer denominador) {
        if (numerador == 0) {
            return "0";
        }
        if (denominador == 0) {
            return "ERR";
        }

        if (numerador > denominador) {

            if (numerador % denominador == 0) {
                return String.format("%d", numerador / denominador);
            } else {
                int novoNumerador = numerador % denominador;
                int inteiro = (numerador - novoNumerador) / denominador;

                return String.format("%d %d/%d", inteiro, novoNumerador, denominador);
            }
        } else if (numerador < denominador) {

            if (calcularMDC(numerador, denominador) > 1) {
                int divisorComum = calcularMDC(numerador, denominador);
                return String.format("%d/%d", numerador / divisorComum, denominador / divisorComum);
            } else {
                return String.format("%d/%d", numerador, denominador);
            }
        }

        return "";
    }

    public static int calcularMDC(int a, int b) {
        while (b != 0) {
            int resto = a % b;
            a = b;
            b = resto;
        }
        return a;
    }
}
