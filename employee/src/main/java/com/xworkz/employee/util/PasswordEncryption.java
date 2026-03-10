package com.xworkz.employee.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class PasswordEncryption {
    private static final String SECRET_KEY = "1234567890123456"; // 16 characters

    public static String encrypt(String password) {
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
}




















































//for example : Hello@123
//Hello
//H-72,e-101,l-108,l-108,o-111,@64,1-49,2-50,3-51(Plain text)
//[71,101,108,108,111,64,49,50,51]
//SECRET_KEY -1234567890123456(convert this to ASCII)
//1-49,2-50,3-51,4-52,5-53,6-54,7-55,8-56,9-57,0-48,1-49,2-50,3-51,4-52,5-53,6-54]
//now it looks like[49,50,51,52,53,54,55,56,57,48,49,50,51,52,53,54]

//here AES Encryption with do some operations like substitution,shifting,mixing,XOR operation
//Result it [-34, 44, 87, 98, 91, -11, 99, 12, 44, -92, 78, 100, 22, 7, -11, 88]->cipher.doFinal(password.getBytes());


//then convert this to BASE64 string
//Base64.getEncoder().encodeToString(encryptedBytes);
//outptu it 3ixXYlv1YwwspE5kFgf1WA==


