package com.github.prathamdby.payroll.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public final class Sha256Util {
    private static final SecureRandom RANDOM = new SecureRandom();

    private Sha256Util() {
    }

    public static String generateSaltBase64() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashWithSaltBase64(String plaintext, String saltBase64) {
        try {
            byte[] salt = Base64.getDecoder().decode(saltBase64);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            digest.update(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest.digest());
        } catch (Exception ex) {
            throw new IllegalStateException("SHA-256 hash failed", ex);
        }
    }
}
