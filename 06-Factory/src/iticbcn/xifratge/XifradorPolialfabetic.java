package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    public static final String abc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public final char[] abecedari = abc.toLowerCase().toCharArray();
    public char[] permutat = new char[abc.length()];
    public Random random;

    public void permutaAlfabet() {
        ArrayList<Character> arrAbc = new ArrayList<>();

        for (char c : abecedari) {
            arrAbc.add(c);
        }

        Collections.shuffle(arrAbc, random);

        for (int i = 0; i < arrAbc.size(); i++) {
            permutat[i] = arrAbc.get(i);
        }

    }

    public void initRandom(Long clau) {
        random = new Random(clau);
    }

    public String xifraPoliAlfa(String msg) {
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

    public String desxifraPoliAlfa(String msg) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong = 0;
        try {
            clauLong = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauLong);
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauLong = 0;
        try {
            clauLong = Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        if (xifrat == null) {
            return null;
        }
        initRandom(clauLong);
        return desxifraPoliAlfa(xifrat.toString());
    }

}
