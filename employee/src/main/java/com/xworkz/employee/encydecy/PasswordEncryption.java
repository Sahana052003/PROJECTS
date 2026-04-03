package com.xworkz.employee.encydecy;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryption implements PasswordEncoder {

    private static final String SECRET_KEY = "1234567890123456"; // 16 characters

    // ENCRYPT
    @Override
    public String encode(String password) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting password", e);
        }
    }

    // DECRYPT
    @Override
    public String decrypt(String encryptedPassword) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting password", e);
        }
    }

    // MATCH
    @Override
    public boolean match(String rawPassword, String encPassword) {
        String decryptedPassword = decrypt(encPassword);
        return rawPassword.equals(decryptedPassword);
    }
}