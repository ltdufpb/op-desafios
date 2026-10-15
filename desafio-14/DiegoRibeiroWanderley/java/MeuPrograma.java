import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MeuPrograma {
  public static void main(String[] args) {

    List<String> operacoes = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
      String linha = br.readLine();

      while (linha != null) {
        operacoes.add(linha);
        linha = br.readLine();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    for (String expressao : operacoes) {
      try {
        String expressaoSemEspacos = expressao.replace(" ", "");

        if (verificarParenteses(expressaoSemEspacos)) {
          System.out.println(calcular(expressaoSemEspacos));
        } else {
          System.out.println("ERR SYNTAX");
        }
      } catch (ArithmeticException | NumberFormatException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static boolean verificarParenteses(String expressao) {
    int n = expressao.length() - 1;
    int contParenteses = 0;
    for (int i = 0; i <= n; i++) {
      if (expressao.charAt(i) == '(') {
        contParenteses++;
      } else if (expressao.charAt(i) == ')') {
        contParenteses--;
      }
      if (contParenteses < 0) {
        return false;
      }
    }
    if (contParenteses == 0) {
      return true;
    }
    return false;
  }

  public static String calcular(String expressao) {
    int ultimoAberto = expressao.lastIndexOf('(');

    if (ultimoAberto != -1) {
      int primeiroFechado = expressao.indexOf(')', ultimoAberto);

      if (primeiroFechado != -1) {
        String subExpressao = expressao.substring(ultimoAberto + 1, primeiroFechado);
        String subResultado = fazerConta(subExpressao);
        String novaExpressao =
            expressao.substring(0, ultimoAberto)
                + subResultado
                + expressao.substring(primeiroFechado + 1);
        return calcular(novaExpressao);
      }
    }
    return fazerConta(expressao);
  }

  public static String fazerConta(String subExpressao) {

    if (subExpressao.matches("-?\\d+")) {
      return subExpressao;
    }

    int potencia = subExpressao.indexOf('^');
    int divisao = subExpressao.indexOf('/');
    int multiplicacao = subExpressao.indexOf('*');
    int subtracao = subExpressao.indexOf('-', 1);
    int soma = subExpressao.indexOf('+');

    String novaString;

    if (potencia != -1) {
      novaString = operarOperandos(potencia, subExpressao, '^');
      return fazerConta(novaString);
    } else if (divisao != -1 || multiplicacao != -1) {
      if ((multiplicacao < divisao && multiplicacao != -1) || divisao == -1) {
        novaString = operarOperandos(multiplicacao, subExpressao, '*');
      } else {
        novaString = operarOperandos(divisao, subExpressao, '/');
      }
      return fazerConta(novaString);
    } else if (soma != -1 || subtracao != -1) {
      if ((soma < subtracao && soma != -1) || subtracao == -1) {
        novaString = operarOperandos(soma, subExpressao, '+');
      } else {
        novaString = operarOperandos(subtracao, subExpressao, '-');
      }
      return fazerConta(novaString);
    }

    return subExpressao;
  }

  public static String operarOperandos(int index, String subExpressao, char simbolo) {
    int inicio = index - 1;
    int fim = index + 1;

    while (inicio >= 0 && Character.isDigit(subExpressao.charAt(inicio))) {
      inicio--;
    }

    while (fim < subExpressao.length() && Character.isDigit(subExpressao.charAt(fim))) {
      fim++;
    }

    int n1;
    int n2;
    try {
      n1 = Integer.parseInt(subExpressao.substring(inicio + 1, index));
      n2 = Integer.parseInt(subExpressao.substring(index + 1, fim));
    } catch (NumberFormatException e) {
      throw new NumberFormatException("ERR SYNTAX");
    }

    if (n2 == 0 && simbolo == '/') {
      throw new ArithmeticException("ERR DIVBYZERO");
    }

    int resultado =
        (int)
            (simbolo == '^'
                ? Math.pow(n1, n2)
                : simbolo == '/'
                    ? n1 / n2
                    : simbolo == '*'
                        ? n1 * n2
                        : simbolo == '-' ? n1 - n2 : simbolo == '+' ? n1 + n2 : 0);
    return subExpressao.substring(0, inicio + 1) + resultado + subExpressao.substring(fim);
  }
}
