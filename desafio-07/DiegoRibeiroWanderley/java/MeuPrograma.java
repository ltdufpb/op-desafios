import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MeuPrograma {

    private static final int BUFFER_SIZE = 1024 * 1024; // 1 MB por vez

    public static void main(String[] args) {

        String caminho = args[0];
        List<Long> posicoes = new ArrayList<>();

        try (RandomAccessFile raf = new RandomAccessFile(caminho, "r")) {

            long tamanho = raf.length();

            posicoes.add(0L);
            byte[] buffer = new byte[BUFFER_SIZE];
            long posicaoGlobal = 0;
            int bytesLidos;

            while ((bytesLidos = raf.read(buffer)) != -1) {
                for (int i = 0; i < bytesLidos; i++) {
                    if (buffer[i] == '\n') {
                        long proximaLinha = posicaoGlobal + i + 1;
                        if (proximaLinha < tamanho) {
                            posicoes.add(proximaLinha);
                        }
                    }
                }
                posicaoGlobal += bytesLidos;
            }

            BufferedWriter bw =
                    new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

            for (int i = posicoes.size() - 1; i >= 0; i--) {
                raf.seek(posicoes.get(i));

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int b;
                while ((b = raf.read()) != -1 && b != '\n') {
                    baos.write(b);
                }

                String linha = baos.toString(StandardCharsets.UTF_8.name());
                if (linha.endsWith("\r")) {
                    linha = linha.substring(0, linha.length() - 1);
                }

                bw.write(linha);
                bw.newLine();
            }

            bw.flush();

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
