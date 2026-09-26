public class RotX {
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
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifrat\n---------");

        for (String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRotX(msg, index));
        }
    }

    public static String xifraRotX(String text, int index) {
        String newText = "";

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char c = text.charAt(i);

            for (int x = 0; x < majuscules.length; x++) {
                if (c == majuscules[x]) {
                    if (x + 13 >= majuscules.length) {
                        int val = (x + index) % majuscules.length;
                        newText += majuscules[val];
                        found = true;
                        break;
                    } else {
                        newText += majuscules[x + 13];
                        found = true;
                        break;
                    }
                }
            }

            if (found == false) {
                for (int x = 0; x < minuscules.length; x++) {
                    if (c == minuscules[x]) {
                        if (x + index >= minuscules.length) {
                            int val = (x + index) % minuscules.length;
                            newText += minuscules[val];
                            found = true;
                            break;
                        } else {
                            newText += minuscules[x + 13];
                            found = true;
                            break;
                        }
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
                    if (x - index < 0) {
                        int val = x - index + majuscules.length;
                        newText += majuscules[val];
                        found = true;
                        break;
                    } else {
                        newText += majuscules[x - index];
                        found = true;
                        break;
                    }
                }
            }

            if (found == false) {
                for (int x = 0; x < minuscules.length; x++) {
                    if (c == minuscules[x]) {
                        if (x - index < 0) {
                            int val = x - index + minuscules.length;
                            newText += minuscules[val];
                            found = true;
                            break;
                        } else {
                            newText += minuscules[x - index];
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (found == false) {
                newText += c;
            }
        }

        return newText;
    }
}
