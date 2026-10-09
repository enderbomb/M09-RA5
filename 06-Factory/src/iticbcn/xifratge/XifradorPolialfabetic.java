package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic {
    public static String abc ="AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] abecedari = abc.toLowerCase().toCharArray();
    public static char[] permutat = new char[abc.length()];
    public static final long clauSecreta = 341252132;
    public static Random random;
    public static void main(String[] args) {
        String msgs[] = {
                "Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n------------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void permutaAlfabet() {
        ArrayList<Character> arrAbc = new ArrayList<>();

        for (char c : abecedari) {
            arrAbc.add(c);
        }

        Collections.shuffle(arrAbc, random);

        for (int i = 0; i < arrAbc.size(); i++) {
            permutat[i] = arrAbc.get(i);
        }

    }

    public static void initRandom(Long clau) {
        random = new Random(clau);
    }

    public static String xifraPoliAlfa(String msg) {
        String newText = "";

        for (char c : msg.toCharArray()) {
            permutaAlfabet();
            boolean trobat = false;
            for (int i = 0; i < abecedari.length; i++) {
                if (c == abecedari[i]) {
                    newText += permutat[i];
                    trobat = true;
                    break;
                } else if (c == Character.toUpperCase(abecedari[i])) {
                    newText += Character.toUpperCase(permutat[i]);
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                newText += c;
            }
        }
        return newText;
    }

    public static String desxifraPoliAlfa(String msg) {
        String newText = "";

        for (char c : msg.toCharArray()) {
            permutaAlfabet();
            boolean trobat = false;
            for (int i = 0; i < abecedari.length; i++) {
                if (c == permutat[i]) {
                    newText += abecedari[i];
                    trobat = true;
                    break;
                } else if (c == Character.toUpperCase(permutat[i])) {
                    newText += Character.toUpperCase(abecedari[i]);
                    trobat = true;
                    break;
                }
            }
            if (!trobat) {
                newText += c;
            }
        }
        return newText;
    }

}
