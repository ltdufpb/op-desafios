import java.util.Scanner;

public class Tabuleiro {
    public static void main(String[] args) {
        final int LIMIT = 8;
        Scanner scan = new Scanner(System.in);
        int[][] tabuleiro = new int[LIMIT][LIMIT];
        int[] contador = new int[7];
        String[] pecas = {
                "Vazio",
                "Peão",
                "Bispo",
                "Cavalo",
                "Torre",
                "Rainha",
                "Rei"
        };
        for(int i = 0; i < tabuleiro.length; i++) {
            for(int j = 0; j < tabuleiro[0].length; j++) {
                System.out.print("Informe um número: ");
                tabuleiro[i][j] = scan.nextInt();
            }
        }
        int peca;
        for(int i = 0; i < tabuleiro.length; i++) {
            for(int j = 0; j < tabuleiro[0].length; j++) {
                peca = tabuleiro[i][j];
                contador[peca]++;
            }
        }

        for (int i = 1; i < contador.length; i++) {
            System.out.println(pecas[i] + ": " + contador[i]);
        }
        scan.close();
    }
}
