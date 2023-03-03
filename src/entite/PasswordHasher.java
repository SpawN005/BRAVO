package entite;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordHasher {
    private static final int SALT_LENGTH = 16; // length of salt in bytes
    private static final int ITERATIONS = 10000; // number of iterations
    
    private byte[] salt;
    
    public PasswordHasher() {
        // generate a random salt
        SecureRandom random = new SecureRandom();
        salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
    }
    
    public byte[] getSalt() {
        return salt;
    }
    
    /**
     *
     * @param password
     * @return
     */
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}