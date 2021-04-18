package fr.univ.shaper.graphic.exception;

public class BadGraphicContextException extends Exception {

    public BadGraphicContextException(String message) {
        super(message);
    }

    public BadGraphicContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
