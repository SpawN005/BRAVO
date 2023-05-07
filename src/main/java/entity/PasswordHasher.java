package entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHasher {
    private static final int SALT_LENGTH = 16; // length of salt in bytes
    private static final int ITERATIONS = 10000; // number of iterations
    
    private byte[] salt;
    
    public PasswordHasher() {

    }
    
    public byte[] getSalt() {
        return salt;
    }
    
    /**
     *
     * @param password
     * @return
     */
    public static StringBuilder hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return  hexString;
    }




}