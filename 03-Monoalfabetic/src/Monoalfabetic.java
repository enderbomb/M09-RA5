import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {
    public static String abc ="AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] alfabetPermutat = permutaAlfabet(abc);
    static void main(String[] args) {
        String[] msgs = {"Test 01 àrbitre, coixi, Perímetre", "Test 02 Taüll, DÍA, año","Test 03 Peça, Örrius, Bòvila"};
        ArrayList<String> asd = new ArrayList<>();
        for (char c : abc.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (char c : alfabetPermutat) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Xifratge:");
        for (String s : msgs) {

            String msg = xifraMonoAlfa(s);
            System.out.printf("%-34s -> %s\n", s, msg);
            asd.add(msg);
        }
        System.out.println("Desxifratge:");
        for (String s : asd) {

            String msg = desxifraMonoAlfa(s);
            System.out.printf("%-34s -> %s\n", s, msg);
        }
    }

    public static char[] permutaAlfabet(String alfabet) {
        ArrayList<Character> arrAbc = new ArrayList<>();

        for (char c : alfabet.toCharArray()) {
            arrAbc.add(c);
        }

        Collections.shuffle(arrAbc);

        char[] resultat = new char[arrAbc.size()];

        for (int i = 0; i < arrAbc.size(); i++) {
            resultat[i] = arrAbc.get(i);
        }

        return resultat;
    }

    public static String xifraMonoAlfa(String cadena) {
        String newText = "";
        for (int i = 0; i < cadena.length(); i++) {
            boolean found = false;
            if (found == false) {
                for (int x = 0; x < abc.length(); x++) {
                    if (cadena.charAt(i) == abc.charAt(x)) {
                        newText += alfabetPermutat[x];
                        found = true;
                        break;
                    }
                }

                for (int x = 0; x < abc.length(); x++) {
                    if (cadena.charAt(i) == abc.toLowerCase().charAt(x)) {
                        newText += Character.toLowerCase(alfabetPermutat[x]);
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    newText += cadena.charAt(i);
                }
            }

        }
        return newText;
    }

    public static String desxifraMonoAlfa(String cadena) {
        String newText = "";
        for (int i = 0; i < cadena.length(); i++) {
            boolean found = false;
            if (found == false) {
                for (int x = 0; x < alfabetPermutat.length; x++) {
                    if (cadena.charAt(i) == alfabetPermutat[x]) {
                        newText += abc.charAt(x);
                        found = true;
                        break;
                    }
                }

                for (int x = 0; x < alfabetPermutat.length; x++) {
                    if (cadena.charAt(i) == Character.toLowerCase(alfabetPermutat[x])) {
                        newText += Character.toLowerCase(abc.charAt(x));
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    newText += cadena.charAt(i);
                }
            }

        }
        return newText;
    }

}