import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;

public class BigBase {
    private static final String ALFABETO = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static BigInteger LIMITE_MAXIMO;

    public static void main(String[] args) {
        LIMITE_MAXIMO = calcularLimiteMaximo();
        if (args.length < 1) {
            System.out.println("Erro: Digite o nome do arquivo de entrada.");
            System.out.println("Exemplo: java BigBaseConverter entradas.txt");
            return;
        }
        String nomeArquivo = args[0];
        Scanner scanner = null;
        try {
            File arquivo = new File(nomeArquivo);
            scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] partes = linha.split("\\s+");
                if (partes.length < 3) {
                    System.out.println("???");
                    continue;
                }
                String baseInStr = partes[0];
                String baseOutStr = partes[1];
                String numIn = partes[2];
                String resultado = processarConversao(baseInStr, baseOutStr, numIn);
                System.out.println(resultado);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: O arquivo '" + nomeArquivo + "' não foi encontrado.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static String processarConversao(String baseInStr, String baseOutStr, String numIn) {
        if (!eNumeroNatural(baseInStr) || !eNumeroNatural(baseOutStr)) {
            return "???";
        }
        int bIn = Integer.parseInt(baseInStr);
        int bOut = Integer.parseInt(baseOutStr);
        if (bIn < 2 || bIn > 62 || bOut < 2 || bOut > 62) {
            return "???";
        }
        if (numIn.contains("-")) {
            return "???";
        }
        BigInteger numDecimal = BigInteger.ZERO;
        BigInteger baseInBI = BigInteger.valueOf(bIn);
        for (int i = 0; i < numIn.length(); i++) {
            char caractere = numIn.charAt(i);
            int valorDigito = ALFABETO.indexOf(caractere);
            if (valorDigito == -1 || valorDigito >= bIn) {
                return "???";
            }
            numDecimal = numDecimal.multiply(baseInBI).add(BigInteger.valueOf(valorDigito));
        }
        if (numDecimal.compareTo(LIMITE_MAXIMO) > 0) {
            return "???";
        }
        if (numDecimal.equals(BigInteger.ZERO)) {
            return "0";
        }
        StringBuilder numSaida = new StringBuilder();
        BigInteger baseOutBI = BigInteger.valueOf(bOut);
        while (numDecimal.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divisao = numDecimal.divideAndRemainder(baseOutBI);
            numDecimal = divisao[0];
            int resto = divisao[1].intValue();
            numSaida.insert(0, ALFABETO.charAt(resto));
        }
        return numSaida.toString();
    }

    private static boolean eNumeroNatural(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static BigInteger calcularLimiteMaximo() {
        BigInteger limite = BigInteger.ZERO;
        BigInteger base62 = BigInteger.valueOf(62);
        int valorZ = ALFABETO.indexOf('z');
        for (int i = 0; i < 30; i++) {
            limite = limite.multiply(base62).add(BigInteger.valueOf(valorZ));
        }
        return limite;
    }
}