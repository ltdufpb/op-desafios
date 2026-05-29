import java.util.ArrayList;
import java.util.List;
 
public class DesafioPalindromico {
 
    private static final int MAX_DIGITS_BONUS = 100_000;
 
    private static final String MAX_VALUE_STD = "18446744073709551615";
 
    public static void main(String[] args) {
        String start;
        String end;
 
        switch (args.length) {
            case 2 -> {
                start = args[0].trim();
                end   = args[1].trim();
 
                if (!isValidPositiveInteger(start) || !isValidPositiveInteger(end)) {
                    System.out.println("Os valores informados devem ser inteiros positivos.");
                    return;
                }

                start = removeLeadingZeros(start);
                end   = removeLeadingZeros(end);
 
                if (start.equals("0") || end.equals("0")) {
                    System.out.println("Os valores informados devem ser inteiros positivos.");
                    return;
                }
 
                boolean isBonusMode = start.length() > 19 || end.length() > 19;
 
                if (!isBonusMode) {
                    if (comparar(start, MAX_VALUE_STD) > 0 || comparar(end, MAX_VALUE_STD) > 0) {
                        System.out.println("Modo padrão: valores não podem ultrapassar " + MAX_VALUE_STD);
                        System.out.println("Para números maiores, use o modo bônus (mais de 19 dígitos).");
                        return;
                    }
                } else {
                    if (start.length() > MAX_DIGITS_BONUS || end.length() > MAX_DIGITS_BONUS) {
                        System.out.println("Modo bônus: limite de " + MAX_DIGITS_BONUS + " algarismos por número.");
                        return;
                    }
                }
 
                if (comparar(start, end) > 0) {
                    System.out.println("O valor inicial não pode ser maior que o valor final.");
                    return;
                }
            }
            case 0 -> {
                start = "1";
                end   = "2000";
            }
            default -> {
                System.out.println("Uso: java DesafioPalindromico <inicio> <fim>");
                return;
            }
        }
 
        List<String> palindromes = verificarPalindromicos(start, end);
        System.out.println(palindromes);
    }
    private static List<String> verificarPalindromicos(String inicio, String fim) {
        List<String> lista = new ArrayList<>();
        for (String i = inicio; comparar(i, fim) <= 0; i = incrementar(i)) {
            if (isPalindromo(i)) {
                lista.add(i);
            }
        }
        return lista;
    }
    public static boolean isPalindromo(String numero) {
        int tamanho = numero.length();
        for (int i = 0; i < tamanho / 2; i++) {
            if (numero.charAt(i) != numero.charAt(tamanho - 1 - i)) {
                return false;
            }
        }
        return true;
    }
 
    static String incrementar(String numero) {
        char[] digits = numero.toCharArray();
        int i = digits.length - 1;
 
        while (i >= 0 && digits[i] == '9') {
            digits[i] = '0';
            i--;
        }
 
        if (i < 0) {
            return "1" + new String(digits);
        }
 
        digits[i]++;
        return new String(digits);
    }
 

    static int comparar(String a, String b) {
        // Número com mais dígitos é maior
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }
        return a.compareTo(b);
    }
 
   static boolean isValidPositiveInteger(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    static String removeLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i);
    }
}
