package fr.univ.shaper.gui.command;

import fr.univ.shaper.gui.model.DrawingBoard;

/**
 * Commande à effectuer pour un tablau de dessin
 */
public interface Command {
    /**
     * Commande à éxécuter sur un tableau
     *
     * @param drawingBoard la tableau qui éxécute la commande
     * @throws UnperformedCommandException si une erreur est survenue lors de l'éxécution de la commande
     */
    void runCommand(DrawingBoard drawingBoard) throws UnperformedCommandException;
}
