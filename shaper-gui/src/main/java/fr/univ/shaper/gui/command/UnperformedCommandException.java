package fr.univ.shaper.gui.command;

/**
 * UnperformedCommandException est émise si une commande a échoué
 */
public class UnperformedCommandException extends Exception {

    public UnperformedCommandException(String message) {
        super(message);
    }

    public UnperformedCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
