package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.gui.command.UnperformedCommandException;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

/**
 * Interface contenant les méthodes à implémenter pour un gestionnaire de dessin
 */
public interface DrawingBoardHandler extends Listenable {

    /**
     * Le gestionnaire des factories du module shaper-core
     */
    GraphicFactoryHandler HANDLER = GraphicFactoryHandler.newInstance();

    /**
     * Créé un nouveau dessin de dimension {dimension}
     *
     * @param dimension la dimension du dessin à créer
     */
    void createNewDrawingBoard(Dimension dimension);

    /**
     * Modifie le directeur actuel
     *
     * @param director le nouveau directeur
     */
    void setDirector(Director director);

    /**
     *
     * @return le directeur courrant
     */
    Director getDirector();

    /**
     * Ouvre un fichier de dessin
     *
     * @param file le fichier à ouvrir
     * @param director le directeur à fournir pour la lecture du fichier
     * @throws IOException si une erreur de lecture survient
     */
    void openDrawingBoard(File file, Director director) throws IOException;

    /**
     * Sauvegarde le dessin actuel si celui-ci n'est pas un nouveau dessin
     *
     * @throws IOException si une erreur d'écriture survient
     */
    void saveDrawingBoard() throws IOException;

    /**
     * Sauvegarde le dessin actuel dans un fichier
     *
     * @param file le ficher dans lequel sauvegarder le dessin
     * @param director le directeur à utiliser
     * @throws IOException si une erreur d'écriture survient
     */
    void saveDrawingBoard(File file, Director director) throws IOException;

    /**
     * Le dessin courant
     *
     * @return le dessin courant
     */
    DrawingBoard getDrawingBoard();

    /**
     * Effectue une commande sur le drawingBoard
     *
     * @param command la commande à effectuer
     * @throws UnperformedCommandException si la commande n'a pas pu être effectue
     */
    void doCommand(Command command) throws UnperformedCommandException;

    /**
     * Le crayon utilisé pour dessiner les formes
     *
     * @return le crayon
     */
    Pencil getPencil();

    /**
     * Indique si le dessin n'a pas été enregistré dans un fichier
     *
     * @return true si le dessin n'a pas déjà été enregistré
     */
    boolean drawIsNew();
}
