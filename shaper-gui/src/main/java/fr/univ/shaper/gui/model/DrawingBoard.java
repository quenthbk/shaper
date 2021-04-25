package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.gui.command.UnperformedCommandException;

import java.awt.*;

public interface DrawingBoard extends Listenable {

    Dimension getDimension();

    Pencil getPencil();

    Layer getLayerRoot();

    void setSelectedElement(GraphicElement element);

    /**
     * Renvoie l'élément graphique qui est en train d'être dessiné
     *
     * @return l'élément graphique qui est en train d'être dessiné
     *      null si rien n'est en cours de dessin.
     */
    GraphicElement getSelectedElement();


    /**
     * Permet d'exécuter une commande sur la plache de dessin
     *
     * @param command à éxécuter
     */
    void run(Command command) throws UnperformedCommandException;

    /**
     * Indique s'il est possible de dessiner
     *
     * @return true s'il est possible de dessiner
     */
    boolean canDraw();
}

