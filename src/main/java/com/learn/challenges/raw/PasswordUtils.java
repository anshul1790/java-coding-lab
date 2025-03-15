package com.learn.challenges.raw;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordUtils {

    // Constants for the PBKDF2 algorithm.
    private static final int SALT_LENGTH = 16;       // in bytes
    private static final int ITERATIONS = 10000;       // Number of iterations
    private static final int KEY_LENGTH = 256;         // length in bits

    /**
     * Generates a random salt using SecureRandom.
     *
     * @return a byte array representing the salt.
     */
    public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return salt;
    }

    /**
     * Computes the PBKDF2 hash of the provided password using the given salt.
     *
     * @param password the password to hash.
     * @param salt     the salt.
     * @param iterations the iteration count.
     * @param keyLength the desired key length (in bits).
     * @return the hashed password as a byte array.
     */
    public static byte[] hashPassword(final char[] password, final byte[] salt,
                                      final int iterations, final int keyLength) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password: ", e);
        }
    }

    /**
     * Generates a secure salted hash for the given password.
     * The result is stored in the format: iterations:salt:hash where
     * salt and hash are Base64-encoded.
     *
     * @param password the password to secure.
     * @return the secure password string.
     */
    public static String generateSecurePassword(String password) {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        // Build a string storing iteration, salt, and hash delimited by colons.
        return ITERATIONS + ":" +
                Base64.getEncoder().encodeToString(salt) + ":" +
                Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Verifies a provided password against the stored password.
     *
     * @param providedPassword the password to check.
     * @param storedPassword   the stored password hash in the format: iterations:salt:hash.
     * @return true if the password matches, false otherwise.
     */
    public static boolean verifyPassword(String providedPassword, String storedPassword) {
        // Split the stored password into its parameters.
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = Base64.getDecoder().decode(parts[1]);
        byte[] storedHash = Base64.getDecoder().decode(parts[2]);

        // Hash the provided password using the same salt and iteration count.
        byte[] computedHash = hashPassword(providedPassword.toCharArray(), salt, iterations, storedHash.length * 8);

        // Compare the computed hash with the stored hash in a time-constant manner.
        return Arrays.equals(storedHash, computedHash);
    }

    public static void main(String[] args) {
        // Example usage:
        String password = "MyP@ssw0rd!";
        // Generate the secure hashed password (this is what you'd store in your database)
        String securePassword = generateSecurePassword(password);
        System.out.println("Stored hash: " + securePassword);

        // Later, when verifying the password:
        boolean isMatch = verifyPassword("MyP@ssw0rd!", securePassword);
        System.out.println("Password verification: " + isMatch);

        // Test with an incorrect password.
        boolean isNotMatch = verifyPassword("IncorrectPassword", securePassword);
        System.out.println("Incorrect password verification: " + isNotMatch);
    }
}

