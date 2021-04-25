package fr.univ.shaper.gui.command;

public class UnperformedCommandException extends Exception {

    public UnperformedCommandException(String message) {
        super(message);
    }

    public UnperformedCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
