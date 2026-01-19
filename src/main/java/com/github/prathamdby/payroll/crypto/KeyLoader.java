package com.github.prathamdby.payroll.crypto;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class KeyLoader {
    private static Map<String, String> envCache = null;

    private KeyLoader() {
    }

    public static SecretKey loadAesKey() {
        return loadSecretKey("AES_KEY_B64", "AES");
    }

    public static SecretKey loadTripleDesKey() {
        return loadSecretKey("TDES_KEY_B64", "DESede");
    }

    public static PublicKey loadRsaPublicKey() {
        String keyB64 = requireEnv("RSA_PUBLIC_KEY_B64");
        try {
            byte[] decoded = Base64.getDecoder().decode(keyB64);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to load RSA public key", ex);
        }
    }

    public static PrivateKey loadRsaPrivateKey() {
        String keyB64 = requireEnv("RSA_PRIVATE_KEY_B64");
        try {
            byte[] decoded = Base64.getDecoder().decode(keyB64);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to load RSA private key", ex);
        }
    }

    private static SecretKey loadSecretKey(String envVar, String algorithm) {
        String keyB64 = requireEnv(envVar);
        byte[] decoded = Base64.getDecoder().decode(keyB64);
        return new SecretKeySpec(decoded, algorithm);
    }

    private static String requireEnv(String name) {
        String value = System.getenv(name);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        if (envCache == null) {
            loadEnvFile();
        }

        value = envCache.get(name);
        if (value == null || value.isEmpty()) {
            throw new IllegalStateException("Missing env var: " + name);
        }
        return value;
    }

    private static synchronized void loadEnvFile() {
        if (envCache != null) {
            return;
        }

        envCache = new HashMap<>();

        String catalinaHome = System.getProperty("catalina.home");
        if (catalinaHome == null) {
            catalinaHome = System.getenv("CATALINA_HOME");
        }

        Path[] pathsToTry = {
                catalinaHome != null ? Paths.get(catalinaHome, "webapps", "demo", ".env") : null,
                Paths.get("C:", "xampp", "tomcat", "webapps", "demo", ".env"),
                catalinaHome != null ? Paths.get(catalinaHome, "webapps", "demo", ".env").toAbsolutePath() : null,
                Paths.get(System.getProperty("user.dir"), "demo", ".env"),
                Paths.get(".", "demo", ".env").toAbsolutePath(),
                Paths.get("demo", ".env").toAbsolutePath(),
                Paths.get(".env").toAbsolutePath()
        };

        boolean found = false;
        for (Path envPath : pathsToTry) {
            if (envPath == null)
                continue;
            try {
                Path absPath = envPath.toAbsolutePath().normalize();
                if (Files.exists(absPath)) {
                    System.out.println("Loading .env from: " + absPath);
                    try (BufferedReader reader = Files.newBufferedReader(absPath)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            line = line.trim();
                            if (line.isEmpty() || line.startsWith("#")) {
                                continue;
                            }
                            int eqIdx = line.indexOf('=');
                            if (eqIdx > 0) {
                                String key = line.substring(0, eqIdx).trim();
                                String val = line.substring(eqIdx + 1).trim();
                                envCache.put(key, val);
                            }
                        }
                        found = true;
                        System.out.println("Loaded " + envCache.size() + " keys from .env file");
                        break;
                    }
                }
            } catch (Exception ex) {
                System.err.println("Warning: Failed to check/read .env file: " + envPath + " - " + ex.getMessage());
            }
        }

        if (!found) {
            System.err.println("ERROR: Could not find .env file in any of the expected locations!");
            System.err.println("Tried paths relative to catalina.home: " + catalinaHome);
        }
    }
}
