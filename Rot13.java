public class Rot13 {
    public static char[] majuscules = { 'A', 'Á', 'À', 'B', 'C', 'Ç',
            'D', 'E', 'É', 'È', 'F',
            'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K',
            'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò',
            'P', 'Q', 'R', 'S', 'T', 'U',
            'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç',
            'd', 'e', 'é', 'è', 'f',
            'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k',
            'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò',
            'p', 'q', 'r', 's', 't', 'u',
            'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public static void main(String[] args) {
        String msgs[] = { "ABC", "XYZ", "Hola, Mr. calcot", "Perdó, per tu què és?" };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n-------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifrat\n----------");

        for (String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }

    public static String xifraRot13(String text) {
        String newText = "";

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char c = text.charAt(i);

            for (int x = 0; x < majuscules.length; x++) {
                if (c == majuscules[x]) {
                    if (x + 13 >= majuscules.length) {
                        int val = (x + 13) % majuscules.length;
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
                        if (x + 13 >= minuscules.length) {
                            int val = (x + 13) % minuscules.length;
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

    public static String desxifraRot13(String text) {
        String newText = "";

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            char c = text.charAt(i);

            for (int x = 0; x < majuscules.length; x++) {
                if (c == majuscules[x]) {
                    if (x - 13 < 0) {
                        int val = x - 13 + majuscules.length;
                        newText += majuscules[val];
                        found = true;
                        break;
                    } else {
                        newText += majuscules[x - 13];
                        found = true;
                        break;
                    }
                }
            }

            if (found == false) {
                for (int x = 0; x < minuscules.length; x++) {
                    if (c == minuscules[x]) {
                        if (x - 13 < 0) {
                            int val = x - 13 + minuscules.length;
                            newText += minuscules[val];
                            found = true;
                            break;
                        } else {
                            newText += minuscules[x - 13];
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
