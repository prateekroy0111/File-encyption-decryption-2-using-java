
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */ 

public class Encrypt_Decrypt_Blowfish {
    public String encrypt(String password, String key) throws 
                  NoSuchAlgorithmException, NoSuchPaddingException,
                   InvalidKeyException, IllegalBlockSizeException, 
                    BadPaddingException, UnsupportedEncodingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
        String encryptedtext = Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
        return encryptedtext;

    }

    public String decrypt(String encryptedtext, String key) 
          throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        byte[] ecryptedtexttobytes = Base64.getDecoder().decode(encryptedtext);
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, KS);
        byte[] decrypted = cipher.doFinal(ecryptedtexttobytes);
        String decryptedString = new String(decrypted, Charset.forName("UTF-8"));
        return decryptedString;

    }
    
    
    String getAlphaString()
    {
        int n = 16;
        byte[] array = new byte[256];
        new Random().nextBytes(array);
  
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
  
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);  
            if (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        
        return r.toString();
    }

    public static void main(String[] args) throws Exception {
        Encrypt_Decrypt_Blowfish obj = new Encrypt_Decrypt_Blowfish();
        
        final String password = "Hello world! end of the text. i dont wanr antt";
        final String key = obj.getAlphaString();
        System.out.println("key: " + key);
        System.out.println("Password: " + password);
        
        String enc_output = obj.encrypt(password, key);
        System.out.println("Encrypted text: " + enc_output);
        String dec_output = obj.decrypt(enc_output, key);
        System.out.println("Decrypted text: " + dec_output);

    }
}
