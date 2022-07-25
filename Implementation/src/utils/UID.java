package utils;

import java.util.Random;

public class UID {

    /**
     *
     * Generates a random UID from the input size given.
     *
     * @param n: Size of the UID.
     * @return String: A randomly generated UID with the size n.
     */
	public static String generateUID(int n) {
		StringBuilder str=new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
	}
}
