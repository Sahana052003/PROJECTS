package com.xworkz.employee.encydecy;

public interface PasswordEncoder {
    // encode the plain password
    String encode(String password);

    // decrypt the encrypted password
    String decrypt(String encryptedPassword);

    // match plain password with encrypted password
    boolean match(String rawPassword, String encPassword);
}
