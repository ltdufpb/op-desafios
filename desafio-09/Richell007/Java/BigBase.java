import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class BigBase {

    static final String DIGITOS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final int BASE_MIN = 2;
    static final int BASE_MAX = 62;
    static final BigInteger LIMITE_MAX = converterParaDecimal("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", 62);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(
                    "Nenhum caminho foi fornecido, execute o programa usando"
                            + " 'java BigBase <caminho-absoluto>'");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    continue;
                }
                System.out.println(processar(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String processar(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length != 3) {
            return "???";
        }

        int baseEntrada;
        int baseSaida;
        try {
            baseEntrada = Integer.parseInt(partes[0]);
            baseSaida = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            return "???";
        }

        String numero = partes[2];

        if (baseEntrada < BASE_MIN
                || baseEntrada > BASE_MAX
                || baseSaida < BASE_MIN
                || baseSaida > BASE_MAX) {
            return "???";
        }

        if (numero.startsWith("-")) {
            return "???";
        }

        BigInteger decimal = converterParaDecimal(numero, baseEntrada);
        if (decimal == null) {
            return "???";
        }

        if (decimal.compareTo(LIMITE_MAX) > 0) {
            return "???";
        }

        return converterParaBase(decimal, baseSaida);
    }

    static BigInteger converterParaDecimal(String numero, int base) {
        BigInteger resultado = BigInteger.ZERO;
        BigInteger baseBig = BigInteger.valueOf(base);
        for (int i = 0; i < numero.length(); i++) {
            int valor = valorDigito(numero.charAt(i));
            if (valor < 0 || valor >= base) {
                return null;
            }
            resultado = resultado.multiply(baseBig).add(BigInteger.valueOf(valor));
        }
        return resultado;
    }

    static String converterParaBase(BigInteger numero, int base) {
        if (numero.equals(BigInteger.ZERO)) {
            return "0";
        }
        BigInteger baseBig = BigInteger.valueOf(base);
        StringBuilder sb = new StringBuilder();
        while (numero.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = numero.divideAndRemainder(baseBig);
            sb.append(DIGITOS.charAt(divmod[1].intValue()));
            numero = divmod[0];
        }
        return sb.reverse().toString();
    }

    static int valorDigito(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 36;
        }
        return -1;
    }
}