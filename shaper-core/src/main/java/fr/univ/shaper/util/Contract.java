package fr.univ.shaper.util;

public class Contract {

    public static void assertThat(boolean o, String message) {
        if (! o) {
            throw new AssertionError(message);
        }
    }
}
