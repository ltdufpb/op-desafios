import java.util.Scanner;
 
public class Fracoes {
 
    private static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
 
    private static String simplificar(int numerador, int denominador) {
        if (denominador == 0) {
            return "ERR";
        }
 
        // Numero inteiro sem denominador
        if (denominador == 1) {
            return String.valueOf(numerador);
        }
 
        int mdc = mdc(numerador, denominador);
        numerador /= mdc;
        denominador /= mdc;
 
        // Divisao exata
        if (denominador == 1) {
            return String.valueOf(numerador);
        }
 
        // Fracao propria (menor que 1)
        if (numerador < denominador) {
            return numerador + "/" + denominador;
        }
 
        // Numero misto
        int inteiro = numerador / denominador;
        int resto = numerador % denominador;
 
        if (resto == 0) {
            return String.valueOf(inteiro);
        }
 
        return inteiro + " " + resto + "/" + denominador;
    }
 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
 
                if (linha.isEmpty()) {
                    continue;
                }
 
                try {
                    String[] partes = linha.split("/");
                    int numerador = Integer.parseInt(partes[0].trim());
                    int denominador = (partes.length == 2) ? Integer.parseInt(partes[1].trim()) : 1;
                    System.out.println(simplificar(numerador, denominador));
                } catch (NumberFormatException e) {
                    System.out.println("ERR");
                }
            }
        }
    }
}
 
