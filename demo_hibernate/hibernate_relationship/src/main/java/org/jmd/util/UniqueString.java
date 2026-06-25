package org.jmd.util;

import java.util.Random;

public class UniqueString {
    public static String getUniqueAadhar() {
        Random random = new Random();
        StringBuilder aadhar = new StringBuilder();

        // 1. First digit must be between 2 and 9 (Aadhaar cannot start with 0 or 1)
        int firstDigit = 2 + random.nextInt(8); // Generates 2 to 9
        aadhar.append(firstDigit);

        // 2. Generate the next 11 digits (0 to 9) to complete the 12-digit number
        for (int i = 0; i < 11; i++) {
            int digit = random.nextInt(10); // Generates 0 to 9
            aadhar.append(digit);
        }

        return aadhar.toString();
    }
}
