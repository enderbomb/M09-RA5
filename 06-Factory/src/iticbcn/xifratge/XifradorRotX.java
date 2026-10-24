package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    public static final String abc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private char[] majuscules = abc.toCharArray();
    private char[] minuscules = abc.toLowerCase().toCharArray();

    public String xifraRotX(String text, int index) {
        String newText = "";

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char c = text.charAt(i);

            for (int x = 0; x < majuscules.length; x++) {
                if (c == majuscules[x]) {
                    int val = (x + index) % majuscules.length;
                    newText += majuscules[val];
                    found = true;
                    break;
                }
            }

            if (found == false) {
                for (int x = 0; x < minuscules.length; x++) {
                    if (c == minuscules[x]) {
                        int val = (x + index) % minuscules.length;
                        newText += minuscules[val];
                        found = true;
                        break;
                    }
                }
            }

            if (found == false) {
                newText += c;
            }
        }

        return newText;
    }

    public String desxifraRotX(String text, int index) {
        String newText = "";

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char c = text.charAt(i);

            for (int x = 0; x < majuscules.length; x++) {
                if (c == majuscules[x]) {
                    int val = (x - index + majuscules.length) % majuscules.length;
                    newText += majuscules[val];
                    found = true;
                    break;
                }
            }

            if (found == false) {
                for (int x = 0; x < minuscules.length; x++) {
                    if (c == minuscules[x]) {
                        int val = (x - index + minuscules.length) % minuscules.length;
                        newText += minuscules[val];
                        found = true;
                        break;
                    }
                }
            }

            if (found == false) {
                newText += c;
            }
        }

        return newText;
    }

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int n = Integer.parseInt(clau);
            if (n < 0 || n > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return n;
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int index = validaClau(clau);
        return new TextXifrat(xifraRotX(msg, index).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int index = validaClau(clau);
        if (xifrat == null) {
            return null;
        }
        return desxifraRotX(xifrat.toString(), index);
    }
}
