import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Tac {

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(
                    "Nenhum caminho foi fornecido, execute o programa usando 'java Tac"
                            + " <caminho-absoluto>'");
            return;
        }

        File arquivo = new File(args[0]);
        if (!arquivo.exists()) {
            System.out.println("Arquivo nao encontrado.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(arquivo, "r")) {
            PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
            imprimirInvertido(raf, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirInvertido(RandomAccessFile raf, PrintStream out)
            throws IOException {
        long posicao = raf.length();
        byte[] buffer = new byte[BUFFER_SIZE];
        byte[] linha = new byte[BUFFER_SIZE];
        int tamanhoLinha = 0;

        while (posicao > 0) {
            int aLer = (int) Math.min(BUFFER_SIZE, posicao);
            posicao -= aLer;
            raf.seek(posicao);
            raf.readFully(buffer, 0, aLer);

            for (int i = aLer - 1; i >= 0; i--) {
                byte b = buffer[i];

                if (b == '\n') {
                    if (tamanhoLinha > 0) {
                        linha = inverter(linha, tamanhoLinha);
                        out.write(linha, 0, tamanhoLinha);
                        tamanhoLinha = 0;
                    }
                    out.write('\n');
                } else {
                    if (tamanhoLinha >= linha.length) {
                        linha = expandir(linha);
                    }
                    linha[tamanhoLinha++] = b;
                }
            }
        }

        // Imprime ultima linha se nao terminar com \n
        if (tamanhoLinha > 0) {
            linha = inverter(linha, tamanhoLinha);
            out.write(linha, 0, tamanhoLinha);
            out.write('\n');
        }
    }

    private static byte[] inverter(byte[] array, int tamanho) {
        int esq = 0;
        int dir = tamanho - 1;
        while (esq < dir) {
            byte tmp = array[esq];
            array[esq] = array[dir];
            array[dir] = tmp;
            esq++;
            dir--;
        }
        return array;
    }

    private static byte[] expandir(byte[] array) {
        byte[] novo = new byte[array.length * 2];
        System.arraycopy(array, 0, novo, 0, array.length);
        return novo;
    }
}