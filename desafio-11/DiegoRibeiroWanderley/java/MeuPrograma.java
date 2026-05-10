import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MeuPrograma {
  public static void main(String[] args) {
    String casasDePi = getCasasDePi(args[0]);

    encontrarNumerosPrimosEmPi(casasDePi);
  }

  private static void encontrarNumerosPrimosEmPi(String casasDePi) {
    int N = casasDePi.length();

    int[] dp = new int[N + 1];
    int[] prev = new int[N + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    Arrays.fill(prev, -1);
    dp[0] = 0;

    int bestLen = 0, bestEnd = 0;

    for (int i = 1; i <= N; i++) {
      for (int t = 1; t <= 4 && t <= i; t++) {
        int j = i - t;
        String seg = casasDePi.substring(j, i);
        if (!ePrimo(seg)) continue;

        if (dp[i] == Integer.MAX_VALUE || j < dp[i]) {
          dp[i] = j;
          prev[i] = j;
        }
        if (dp[j] != Integer.MAX_VALUE && dp[j] < dp[i]) {
          dp[i] = dp[j];
          prev[i] = j;
        }
      }

      if (dp[i] != Integer.MAX_VALUE) {
        int len = i - dp[i];
        if (len > bestLen) {
          bestLen = len;
          bestEnd = i;
        }
      }
    }

    List<String> melhorParticao = new ArrayList<>();
    int i = bestEnd;
    while (i > 0 && prev[i] != -1) {
      int j = prev[i];
      melhorParticao.add(casasDePi.substring(j, i));
      i = j;
    }
    Collections.reverse(melhorParticao);

    System.out.println(String.valueOf(melhorParticao).replaceAll("[\\[\\] ,]", ""));
  }

  private static String getCasasDePi(String caminho) {

    String piCompleto = "";

    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      StringBuilder sb = new StringBuilder();

      String linha = br.readLine();
      while (linha != null) {
        sb.append(linha);
        linha = br.readLine();
      }
      br.close();

      piCompleto = sb.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return piCompleto.split("\\.")[1];
  }

  public static boolean ePrimo(String n) {
    if (n.startsWith("0") || n.isEmpty()) return false;
    long num = Long.parseLong(n);
    if (num < 2) return false;
    for (long i = 2; i * i <= num; i++) {
      if (num % i == 0) return false;
    }
    return true;
  }
}
