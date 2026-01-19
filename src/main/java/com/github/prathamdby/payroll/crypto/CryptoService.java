package com.github.prathamdby.payroll.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public final class CryptoService {
  private static final SecretKey AES_KEY = KeyLoader.loadAesKey();
  private static final SecretKey TDES_KEY = KeyLoader.loadTripleDesKey();
  private static final PublicKey RSA_PUBLIC_KEY = KeyLoader.loadRsaPublicKey();
  private static final PrivateKey RSA_PRIVATE_KEY = KeyLoader.loadRsaPrivateKey();

  private CryptoService() {
  }

  public static String encryptPhone(String plaintext) {
    return AesGcmUtil.encryptToBase64(plaintext, AES_KEY);
  }

  public static String decryptPhone(String ciphertext) {
    return AesGcmUtil.decryptFromBase64(ciphertext, AES_KEY);
  }

  public static String encryptSalary(String plaintext) {
    return RsaOaepUtil.encryptToBase64(plaintext, RSA_PUBLIC_KEY);
  }

  public static String decryptSalary(String ciphertext) {
    return RsaOaepUtil.decryptFromBase64(ciphertext, RSA_PRIVATE_KEY);
  }

  public static String encryptBankAccount(String plaintext) {
    return TripleDesUtil.encryptToBase64(plaintext, TDES_KEY);
  }

  public static String decryptBankAccount(String ciphertext) {
    return TripleDesUtil.decryptFromBase64(ciphertext, TDES_KEY);
  }

  public static String encryptNotes(String plaintext) {
    return AesGcmUtil.encryptToBase64(plaintext, AES_KEY);
  }

  public static String decryptNotes(String ciphertext) {
    return AesGcmUtil.decryptFromBase64(ciphertext, AES_KEY);
  }

  public static String generateNotesSalt() {
    return Sha256Util.generateSaltBase64();
  }

  public static String hashNotes(String plaintext, String saltBase64) {
    return Sha256Util.hashWithSaltBase64(plaintext, saltBase64);
  }
}
