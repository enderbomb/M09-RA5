package iticbcn.xifratge;

public class XifradorRotX {
    public static String abc = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    public static char[] majuscules = abc.toCharArray();
    public static char[] minuscules = abc.toLowerCase().toCharArray();

    public static void main(String[] args) {
        String msgs[] = { "ABC", "XYZ", "Hola, Mr. calcot", "Perdó, per tu què és?" };
        String msgsXifrats[] = new String[msgs.length];
        int index = 0;
        System.out.println("\nXifrat\n--------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRotX(msgs[i], index);
            System.out.printf("(%d)%-23s => %s%n", index, msgs[i], msgsXifrats[i]);
            index += 2;
        }

        System.out.println("\nDesxifrat\n---------");
        index = 0;
        for (String msg : msgsXifrats) {
            System.out.printf("(%d)%-23s => %s%n", index, msg, desxifraRotX(msg, index));
            index += 2;
        }

        System.out.println("\nMissatge xifrat: " + msgsXifrats[3]);
        forcaBrutaRotX(msgsXifrats[3]);
    }

    public static String xifraRotX(String text, int index) {
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

    public static String desxifraRotX(String text, int index) {
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

    public static void forcaBrutaRotX(String cadenaXifrada) {
        for (int index = 0; index < majuscules.length; index++) {
            System.out.printf("(%d)->%s%n", index, desxifraRotX(cadenaXifrada, index));
        }
    }
}