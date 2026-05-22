import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Fracao {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                String resultado = processarFracao(linha);
                System.out.println(resultado);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String processarFracao(String linha) {
        String[] partes = linha.split("/");
        long numerador, denominador;

        if(partes.length == 1) {
            numerador = Long.parseLong(partes[0]);
            denominador = 1;
        } else {
            numerador = Long.parseLong(partes[0]);
            denominador = Long.parseLong(partes[1]);
        }

        if (denominador == 0) {
            return "ERR";
        }
        long parteInteiro = numerador/denominador;
        long parteResto = numerador%denominador;
        if(parteResto == 0) {
            return String.valueOf(parteInteiro);
        }

        long divisorComum = mdc(parteResto, denominador);
        long numSimplificado = parteResto / divisorComum;
        long denSimplificado = denominador / divisorComum;
        if (parteInteiro > 0) {
            return parteInteiro + " " + numSimplificado + "/" + denSimplificado;
        } else {

            return numSimplificado + "/" + denSimplificado;
        }

    }

    private static long mdc(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
