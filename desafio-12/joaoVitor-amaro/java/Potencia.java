import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Potencia {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String linha;
            while ((linha = br.readLine()) != null) {
                BigInteger numero = new BigInteger(linha);
                long expoente = pegarExpoente(numero);

                if(expoente != -1) {
                    System.out.println(
                            numero + " true " + expoente);
                } else {
                    System.out.println(numero + " false");
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long pegarExpoente(BigInteger numero) {
        if(numero.compareTo(BigInteger.ZERO) <= 0) {
            return -1;
        }
        boolean ehPotencia =
                numero.and(numero.subtract(BigInteger.ONE))
                        .equals(BigInteger.ZERO);

        if(ehPotencia) {
            return numero.bitLength() - 1;
        }

        return -1;
    }
}
