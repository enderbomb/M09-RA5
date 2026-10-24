package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {

    public static final String abc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public final char[] abecedari = abc.toCharArray();
    public char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(abecedari);
    }

    public char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> arrAbc = new ArrayList<>();

        for (char c : alfabet) {
            arrAbc.add(c);
        }

        Collections.shuffle(arrAbc);

        char[] resultat = new char[arrAbc.size()];

        for (int i = 0; i < arrAbc.size(); i++) {
            resultat[i] = arrAbc.get(i);
        }

        return resultat;
    }

    public String xifraMonoAlfa(String cadena) {
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

    public String desxifraMonoAlfa(String cadena) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (xifrat == null) {
            return null;
        }
        return desxifraMonoAlfa(xifrat.toString());
    }

}
