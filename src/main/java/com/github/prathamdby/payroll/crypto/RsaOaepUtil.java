package com.github.prathamdby.payroll.crypto;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public final class RsaOaepUtil {
    private RsaOaepUtil() {
    }

    public static String encryptToBase64(String plaintext, PublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception ex) {
            throw new IllegalStateException("RSA encrypt failed", ex);
        }
    }

    public static String decryptFromBase64(String encoded, PrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] ciphertext = Base64.getDecoder().decode(encoded);
            byte[] plaintext = cipher.doFinal(ciphertext);
            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new IllegalStateException("RSA decrypt failed", ex);
        }
    }
}
