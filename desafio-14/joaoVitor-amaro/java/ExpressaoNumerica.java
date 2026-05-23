import java.io.*;
import java.util.*;

public class ExpressaoNumerica {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ExpressaoNumerica <arquivo>");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                try {
                    List<String> posfixa = infixaParaPosfixa(linha);
                    long resultado = avaliar(posfixa);
                    System.out.println(resultado);

                } catch (ArithmeticException e) {
                    System.out.println("ERR DIVBYZERO");

                } catch (Exception e) {
                    System.out.println("ERR SYNTAX");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int prioridade(String op) {
        switch (op) {
            case "^":
                return 3;

            case "*":
            case "/":
                return 2;

            case "+":
            case "-":
                return 1;
        }
        return 0;
    }

    static boolean operador(String s) {
        return s.equals("+") ||
                s.equals("-") ||
                s.equals("*") ||
                s.equals("/") ||
                s.equals("^");
    }

    static List<String> tokenizar(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expr.length() &&
                        Character.isDigit(expr.charAt(i))) {
                    num.append(expr.charAt(i));
                    i++;
                }
                tokens.add(num.toString());
                continue;
            }
            if ("+-*/^()".indexOf(c) != -1) {
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }
            throw new RuntimeException();
        }
        return tokens;
    }

    static List<String> infixaParaPosfixa(String expr) {
        List<String> tokens = tokenizar(expr);
        List<String> saida = new ArrayList<>();
        Stack<String> ops = new Stack<>();
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                saida.add(token);
            } else if (operador(token)) {
                while (!ops.isEmpty()
                        && operador(ops.peek())
                        && prioridade(ops.peek()) >= prioridade(token)) {
                    saida.add(ops.pop());
                }
                ops.push(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.isEmpty() &&
                        !ops.peek().equals("(")) {
                    saida.add(ops.pop());
                }
                if (ops.isEmpty()) {
                    throw new RuntimeException();
                }
                ops.pop();
            } else {
                throw new RuntimeException();
            }
        }
        while (!ops.isEmpty()) {
            if (ops.peek().equals("(")) {
                throw new RuntimeException();
            }
            saida.add(ops.pop());
        }
        return saida;
    }

    static long avaliar(List<String> posfixa) {
        Stack<Long> pilha = new Stack<>();
        for (String token : posfixa) {
            if (token.matches("\\d+")) {
                pilha.push(Long.parseLong(token));
            } else {
                if (pilha.size() < 2) {
                    throw new RuntimeException();
                }
                long b = pilha.pop();
                long a = pilha.pop();
                switch (token) {
                    case "+":
                        pilha.push(a + b);
                        break;
                    case "-":
                        pilha.push(a - b);
                        break;
                    case "*":
                        pilha.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException();
                        }
                        pilha.push(a / b);
                        break;
                    case "^":
                        pilha.push((long)Math.pow(a, b));
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        }
        if (pilha.size() != 1) {
            throw new RuntimeException();
        }
        return pilha.pop();
    }
}