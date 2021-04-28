package fr.univ.shaper.util;

public class StringExt {

    /**
     * Met une majuscule à la première lettre de la chaîne de caractère.
     *
     * @param str la chaîne à modifier
     * @return une chaîne de caractère dans la première lettre a une majuscule
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() +  str.substring(1);
    }
}
