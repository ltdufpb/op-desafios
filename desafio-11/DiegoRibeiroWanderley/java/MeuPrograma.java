import java.io.*;
import java.util.*;

public class MeuPrograma {

  public static void main(String[] args) throws IOException {
    String casasDePi = getCasasDePi(args[0]);
    System.out.println(encontrarMaiorSequencia(casasDePi));
  }

  private static String encontrarMaiorSequencia(String pi) {
    int n = pi.length();

    int[] dp = new int[n + 1];

    for (int i = 0; i < n; i++) {
      for (int tam = 1; tam <= 4 && i + tam <= n; tam++) {
        String candidato = pi.substring(i, i + tam);
        if (ePrimo(candidato)) {
          int novoComp = dp[i] + tam;
          if (novoComp > dp[i + tam]) {
            dp[i + tam] = novoComp;
          }
        }
      }
    }

    int melhorFim = -1;
    int melhorComp = 0;
    for (int i = 1; i <= n; i++) {
      if (dp[i] > melhorComp) {
        melhorComp = dp[i];
        melhorFim = i;
      }
    }

    if (melhorFim == -1) return "";
    return pi.substring(melhorFim - melhorComp, melhorFim);
  }

  private static String getCasasDePi(String caminho) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        sb.append(linha);
      }
    }
    return sb.toString().split("\\.")[1];
  }

  public static boolean ePrimo(String n) {
    if (n.isEmpty() || n.startsWith("0")) return false;
    long num = Long.parseLong(n);
    if (num < 2) return false;
    for (long i = 2; i * i <= num; i++) {
      if (num % i == 0) return false;
    }
    return true;
  }
}
