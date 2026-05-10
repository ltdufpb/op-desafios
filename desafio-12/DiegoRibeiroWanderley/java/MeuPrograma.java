import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class MeuPrograma {
  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
      String linha = br.readLine();

      while (linha != null) {

        BigInteger bigInteger = new BigInteger(linha);
        if (bigInteger.signum() > 0 && bigInteger.bitCount() == 1) {
          System.out.println(bigInteger + " true " + bigInteger.getLowestSetBit());
        } else {
          System.out.println(bigInteger + " false");
        }

        linha = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
