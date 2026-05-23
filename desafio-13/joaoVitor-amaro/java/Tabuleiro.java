import java.util.*;

public class Tabuleiro {
    static final int N = 8;
    static boolean[][] visitado = new boolean[N][N];
    static List<String> caminho = new ArrayList<>();
    static int[] dx = {2,2,-2,-2,1,1,-1,-1};
    static int[] dy = {1,-1,1,-1,2,-2,2,-2};

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Tabuleiro a1");
            return;
        }
        String inicio = args[0];
        int col = inicio.charAt(0) - 'a';
        int row = inicio.charAt(1) - '1';
        if (resolver(row, col, 1)) {
            for (String pos : caminho) {
                System.out.println(pos);
            }

        } else {
            System.out.println("Sem solução");
        }
    }

    static boolean resolver(int x, int y, int passo) {
        visitado[x][y] = true;
        caminho.add(posicao(x, y));
        if (passo == 64) {
            return true;
        }
        List<int[]> movimentos = proximosMovimentos(x, y);
        for (int[] mov : movimentos) {
            int nx = mov[0];
            int ny = mov[1];
            if (resolver(nx, ny, passo + 1)) {
                return true;
            }
        }
        visitado[x][y] = false;
        caminho.remove(caminho.size() - 1);

        return false;
    }

    static List<int[]> proximosMovimentos(int x, int y) {
        List<int[]> lista = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (valido(nx, ny)) {
                int grau = contarMovimentos(nx, ny);

                lista.add(new int[]{nx, ny, grau});
            }
        }
        lista.sort(Comparator.comparingInt(a -> a[2]));
        List<int[]> resultado = new ArrayList<>();
        for (int[] mov : lista) {
            resultado.add(new int[]{mov[0], mov[1]});
        }

        return resultado;
    }

    static int contarMovimentos(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (valido(nx, ny)) {
                count++;
            }
        }
        return count;
    }

    static boolean valido(int x, int y) {
        return x >= 0 &&
                x < N &&
                y >= 0 &&
                y < N &&
                !visitado[x][y];
    }

    static String posicao(int x, int y) {
        char coluna = (char)('a' + y);
        char linha = (char)('1' + x);
        return "" + coluna + linha;
    }
}