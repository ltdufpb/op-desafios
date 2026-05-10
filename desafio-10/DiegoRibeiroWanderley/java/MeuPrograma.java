import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MeuPrograma {
  public static void main(String[] args) {
    String caminho = args[0];

    List<String[]> casamento = casamento(caminho);

    for (String[] casal : casamento) {
      pegarRegras(casal[0], casal[1]);
    }
  }

  public static List<String[]> casamento(String caminho) {
    List<String[]> casamento = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

      String linha;

      while ((linha = br.readLine()) != null) {
        String[] linhaDiv = linha.split(",");
        casamento.add(new String[] {linhaDiv[0], linhaDiv[1]});
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return casamento;
  }

  public static void pegarRegras(String caminho, String entrada) {

    List<String[]> regras = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      String linha = br.readLine();

      while (linha != null) {
        String[] linhaSplit = new String[5];

        if (!linha.startsWith(";") && !linha.isBlank()) {
          String[] linhaAtualSplit = linha.split(" ");
          for (int i = 0; i < 5; i++) linhaSplit[i] = linhaAtualSplit[i];
          regras.add(linhaSplit);
        }

        linha = br.readLine();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    maquinaDeTuring(regras, entrada, caminho);
  }

  public static void maquinaDeTuring(List<String[]> regras, String entrada, String caminho) {

    String estado = "0";
    int comeco = 0;

    char[] entradaArray = entrada.replace(" ", "_").toCharArray();

    boolean podeAcabar = false;
    while (!podeAcabar) {
      char c = entradaArray[comeco];
      List<String[]> possiveisCasamentos = new ArrayList<>();
      for (String[] regra : regras) {
        if (Objects.equals(regra[0], estado) || Objects.equals(regra[0], "*")) {
          possiveisCasamentos.add(regra);
        }
      }
      String[] regraEscolhida = getStrings(c, possiveisCasamentos);
      if (regraEscolhida == null) {
        System.out.println(caminho + "," + entrada + "," + "ERR");
        podeAcabar = true;
        continue;
      }

      if (!regraEscolhida[2].equals("*")) {
        entradaArray[comeco] = regraEscolhida[2].charAt(0);
      }

      estado = regraEscolhida[4];

      if (Objects.equals(regraEscolhida[3], "r")) {
        comeco++;
        if ((entradaArray.length - 1) < comeco) {
          char[] addArray = {'_'};
          char[] novoArray = new char[addArray.length + entradaArray.length];
          System.arraycopy(entradaArray, 0, novoArray, 0, entradaArray.length);
          System.arraycopy(addArray, 0, novoArray, entradaArray.length, addArray.length);
          entradaArray = novoArray;
        }
      } else if (Objects.equals(regraEscolhida[3], "l")) {
        comeco--;
        if (comeco < 0) {
          char[] addArray = {'_'};
          char[] novoArray = new char[addArray.length + entradaArray.length];
          System.arraycopy(addArray, 0, novoArray, 0, addArray.length);
          System.arraycopy(entradaArray, 0, novoArray, addArray.length, entradaArray.length);
          entradaArray = novoArray;
          comeco = 0;
        }
      } else if (Objects.equals(regraEscolhida[3], "*")) {
      }

      if (estado.startsWith("halt")) {
        podeAcabar = true;
        System.out.println(
            caminho + "," + entrada + "," + new String(entradaArray).replace("_", " ").trim());
      }
    }
  }

  private static String[] getStrings(char c, List<String[]> possiveisCasamentos) {
    for (String[] regra : possiveisCasamentos) {
      if (Objects.equals(regra[1], String.valueOf(c))) {
        return regra;
      }
    }

    for (String[] regra : possiveisCasamentos) {
      if (Objects.equals(regra[1], String.valueOf('*'))) {
        return regra;
      }
    }

    return null;
  }
}
