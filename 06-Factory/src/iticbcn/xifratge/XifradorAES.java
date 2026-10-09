package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        String msgs[] = {
                "Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto"
        };

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] lol = msg.getBytes(StandardCharsets.UTF_8);
        iv = generaIv();
        IvParameterSpec spec = new IvParameterSpec(iv);
        SecretKeySpec secreto = generaHash(clau);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);

        cipher.init(Cipher.ENCRYPT_MODE, secreto, spec);
        byte[] xifrat = cipher.doFinal(lol);
        byte[] resultat = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(xifrat, 0, resultat, iv.length, xifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bIVIMsgXifrat, String clau) throws Exception {
        byte[] iv2 = extreuIV(bIVIMsgXifrat);

        byte[] msgXifrat = getBytesXifrats(bIVIMsgXifrat);

        IvParameterSpec spec = new IvParameterSpec(iv2);

        SecretKeySpec secreto = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secreto, spec);
        byte[] desxifrat = cipher.doFinal(msgXifrat);

        return new String(desxifrat, StandardCharsets.UTF_8);
    }

    public static byte[] extreuIV(byte[] bIVIMsgXifrat) {
        byte[] iv2 = new byte[MIDA_IV];
        System.arraycopy(bIVIMsgXifrat, 0, iv2, 0, MIDA_IV);
        return iv2;
    }

    public static byte[] getBytesXifrats(byte[] bIVIMsgXifrat) {
        byte[] msgXifrat = new byte[bIVIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIVIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);
        return msgXifrat;
    }
    public static SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }
    public static byte[] generaIv() {
        byte[] nouIV = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nouIV);
        return nouIV;
    }
}