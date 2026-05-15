import java.util.ArrayList;
import java.util.List;

public class MeuPrograma {
    public static void main(String[] args) {
        String posicaoInicial = args[0];
        boolean[][] tabuleiro = new boolean[8][8];
        List<String> movimentos = new ArrayList<>();

        if (passeioDoCavalo(posicaoInicial, tabuleiro, movimentos)) {
            movimentos.forEach(System.out::println);
        }
    }

    public static boolean passeioDoCavalo(
            String posicaoInicial, boolean[][] tabuleiro, List<String> movimentos) {

        int[] position = toPosition(posicaoInicial.charAt(0), posicaoInicial.charAt(1));
        int r = position[0];
        int c = position[1];

        tabuleiro[r][c] = true;
        movimentos.add(posicaoInicial);

        if (movimentos.size() == 64) {
            return true;
        }

        List<int[]> possiveisMovimentos = possiveisMovimentos(r, c);

        possiveisMovimentos.sort(
                (a, b) -> {
                    int saidasA = contarSaidas(a[0], a[1], tabuleiro);
                    int saidasB = contarSaidas(b[0], b[1], tabuleiro);
                    return Integer.compare(saidasA, saidasB);
                });

        for (int[] p : possiveisMovimentos) {
            if (!tabuleiro[p[0]][p[1]]) {
                String proximo = toPosicaoXadrez(p[0], p[1]);
                if (passeioDoCavalo(proximo, tabuleiro, movimentos)) {
                    return true;
                }
            }
        }

        tabuleiro[r][c] = false;
        movimentos.remove(movimentos.size() - 1);
        return false;
    }

    public static int contarSaidas(int linha, int coluna, boolean[][] tabuleiro) {
        int count = 0;
        for (int[] p : possiveisMovimentos(linha, coluna)) {
            if (!tabuleiro[p[0]][p[1]]) {
                count++;
            }
        }
        return count;
    }

    public static String toPosicaoXadrez(int linha, int coluna) {
        return String.valueOf((char) ('a' + coluna)) + (8 - linha);
    }

    public static int[] toPosition(char coluna, char linha) {
        return new int[] {8 - (linha - '0'), coluna - 'a'};
    }

    public static List<int[]> possiveisMovimentos(int coluna, int linha) {
        List<int[]> resultado = new ArrayList<>();

        int[] p1 = new int[] {coluna - 2, linha - 1};
        int[] p2 = new int[] {coluna - 2, linha + 1};
        int[] p3 = new int[] {coluna + 2, linha - 1};
        int[] p4 = new int[] {coluna + 2, linha + 1};
        int[] p5 = new int[] {coluna - 1, linha - 2};
        int[] p6 = new int[] {coluna - 1, linha + 2};
        int[] p7 = new int[] {coluna + 1, linha - 2};
        int[] p8 = new int[] {coluna + 1, linha + 2};

        int[][] possiveis = new int[][] {p1, p2, p3, p4, p5, p6, p7, p8};
        for (int[] possivei : possiveis) {
            if (podeMover(possivei)) {
                resultado.add(possivei);
            }
        }

        return resultado;
    }

    public static boolean podeMover(int[] p) {
        return p[0] <= 7 && p[0] >= 0 && p[1] <= 7 && p[1] >= 0;
    }
}