import java.io.*;
import java.util.*;

public class Anagrama {
    static List<String> palavrasValidas = new ArrayList<>();
    static LinkedHashSet<String> resultados = new LinkedHashSet<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Anagrama \"frase\"");
            return;
        }
        String entrada = args[0].toUpperCase();
        for (char c : entrada.toCharArray()) {
            if (!(c >= 'A' && c <= 'Z') && c != ' ') {
                System.out.println("ERR");
                return;
            }
        }
        entrada = entrada.replace(" ", "");
        int[] letrasEntrada = frequencia(entrada);
        carregarPalavras(letrasEntrada);
        backtracking(letrasEntrada, new ArrayList<>(), 0);

        for (String s : resultados) {
            System.out.println(s);
        }
    }

    static void carregarPalavras(int[] letrasEntrada) {
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim().toUpperCase();
                if (linha.isEmpty()) {
                    continue;
                }
                int[] freq = frequencia(linha);
                if (cabe(freq, letrasEntrada)) {
                    palavrasValidas.add(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void backtracking(int[] restante, List<String> atual, int inicio) {
        if (vazio(restante)) {
            List<String> ordenada = new ArrayList<>(atual);
            Collections.sort(ordenada);
            resultados.add(String.join(" ", ordenada));
            return;
        }

        for (int i = inicio; i < palavrasValidas.size(); i++) {
            String palavra = palavrasValidas.get(i);
            int[] freq = frequencia(palavra);
            if (cabe(freq, restante)) {
                subtrair(restante, freq);
                atual.add(palavra);
                backtracking(restante, atual, i + 1);
                atual.remove(atual.size() - 1);
                somar(restante, freq);
            }
        }
    }

    static int[] frequencia(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'A']++;
        }
        return freq;
    }

    static boolean cabe(int[] palavra, int[] disponivel) {
        for (int i = 0; i < 26; i++) {

            if (palavra[i] > disponivel[i]) {
                return false;
            }
        }
        return true;
    }

    static void subtrair(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            a[i] -= b[i];
        }
    }

    static void somar(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            a[i] += b[i];
        }
    }

    static boolean vazio(int[] freq) {
        for (int n : freq) {

            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}