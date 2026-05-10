import java.util.ArrayList;
import java.util.List;

public class MeuPrograma {
  public static void main(String[] args) {
    int baseEntrada = Integer.parseInt(args[0]);
    int baseSaida = Integer.parseInt(args[1]);

    char[] valoresBaseEntrada = args[2].toCharArray();

    converter(baseEntrada, baseSaida, valoresBaseEntrada).forEach(System.out::print);
  }

  public static List<Character> converter(
      int baseEntrada, int baseSaida, char[] valoresBaseEntrada) {

    if ((baseEntrada < 2 || baseEntrada > 62) || (baseSaida < 2 || baseSaida > 62)) {
      return new ArrayList<>(List.of('?', '?', '?'));
    }

    int somaVerificacao = 0;
    for (char c : valoresBaseEntrada) {
      if (valorInteiro(c) >= baseEntrada) {
        return new ArrayList<>(List.of('?', '?', '?'));
      }
      if (c == '-') {
        return new ArrayList<>(List.of('?', '?', '?'));
      }
      somaVerificacao += valorInteiro(c);
    }
    if (somaVerificacao >= 61 * 30) {
      return new ArrayList<>(List.of('?', '?', '?'));
    }

    long soma = 0;
    for (int i = 0; i < valoresBaseEntrada.length; i++) {
      soma +=
          valorInteiro(valoresBaseEntrada[i]) * pow(baseEntrada, valoresBaseEntrada.length - 1 - i);
    }

    List<Character> resultado = new ArrayList<>();
    while (soma != 0) {
      long resto = soma % baseSaida;
      soma = soma / baseSaida;
      resultado.add(valorASCII(resto));
    }

    resultado = resultado.reversed();
    return resultado;
  }

  public static Integer valorInteiro(char c) {
    if (c <= 57 && c >= 48) {
      return Integer.parseInt(String.valueOf(c));
    } else if (c <= 90 && c >= 65) {
      return c - 55;
    } else if (c <= 122 && c >= 97) {
      return c - 61;
    }

    return 0;
  }

  public static char valorASCII(long a) {
    if (a >= 0 && a <= 9) {
      return (char) (a + 48);
    } else if (a <= 35) {
      return (char) (a + 55);
    } else if (a <= 61) {
      return (char) (a + 61);
    }

    return '?';
  }

  public static long pow(int a, int b) {
    long resultado = 1;
    for (int i = b; i > 0; i--) {
      resultado *= a;
    }
    return resultado;
  }
}
