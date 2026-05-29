import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class Anagrama {

    private static int[] contarLetras(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'A']++;
        }
        return freq;
    }
 
    private static boolean cabe(int[] disponivel, int[] sub) {
        for (int i = 0; i < 26; i++) {
            if (sub[i] > disponivel[i]) {
                return false;
            }
        }
        return true;
    }
 
    private static int[] subtrair(int[] disponivel, int[] sub) {
        int[] resultado = Arrays.copyOf(disponivel, 26);
        for (int i = 0; i < 26; i++) {
            resultado[i] -= sub[i];
        }
        return resultado;
    }
 
    private static boolean vazio(int[] freq) {
        for (int f : freq) {
            if (f != 0) {
                return false;
            }
        }
        return true;
    }
 
    public static void validarEntrada(String expressao) {
        for (char c : expressao.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                System.out.println("Erro: a expressao contem caracteres invalidos.");
                System.exit(1);
            }
            if (c != ' ' && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                System.out.println("Erro: a expressao contem caracteres invalidos.");
                System.exit(1);
            }
        }
    }
 
    public static Map<String, int[]> carregarPalavrasValidas(String arquivo, int[] freqDisponivel)
            throws IOException {
        Map<String, int[]> palavras = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            linha = linha.trim().toUpperCase();
            if (!linha.matches("[A-Z]+")) {
                continue;
            }
            int[] freq = contarLetras(linha);
            if (cabe(freqDisponivel, freq)) {
                palavras.put(linha, freq);
            }
        }
        reader.close();
        return palavras;
    }
 
    private static void buscar(
            int[] letrasRestantes,
            List<String> palavrasDisponiveis,
            Map<String, int[]> freqMap,
            List<String> combinacaoAtual,
            Set<String> resultados,
            int indiceMinimo) {
 
        if (vazio(letrasRestantes)) {
            List<String> sorted = new ArrayList<>(combinacaoAtual);
            Collections.sort(sorted);
            resultados.add(String.join(" ", sorted));
            return;
        }
 
        for (int i = indiceMinimo; i < palavrasDisponiveis.size(); i++) {
            String palavra = palavrasDisponiveis.get(i);
            int[] freq = freqMap.get(palavra);
            if (cabe(letrasRestantes, freq)) {
                combinacaoAtual.add(palavra);
                buscar(
                        subtrair(letrasRestantes, freq),
                        palavrasDisponiveis,
                        freqMap,
                        combinacaoAtual,
                        resultados,
                        i); 
                combinacaoAtual.remove(combinacaoAtual.size() - 1);
            }
        }
    }
 
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Anagrama \"<expressao>\"");
            System.exit(1);
        }
 
        String expressao = args[0];
        validarEntrada(expressao);
        expressao = expressao.replaceAll(" ", "").toUpperCase();
 
        int[] freqDisponivel = contarLetras(expressao);
 
        Map<String, int[]> freqMap;
        try {
            freqMap = carregarPalavrasValidas("words.txt", freqDisponivel);
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo words.txt.");
            System.exit(1);
            return;
        }
        List<String> palavras = new ArrayList<>(freqMap.keySet());
        palavras.sort((a, b) -> b.length() - a.length());
 
        Set<String> resultados = new HashSet<>();
        buscar(freqDisponivel, palavras, freqMap, new ArrayList<>(), resultados, 0);
 
        List<String> sorted = new ArrayList<>(resultados);
        Collections.sort(sorted);
        for (String anagrama : sorted) {
            System.out.println(anagrama);
        }
    }
}
