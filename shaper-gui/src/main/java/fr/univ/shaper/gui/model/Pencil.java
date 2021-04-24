package fr.univ.shaper.gui.model;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Pencil extends Listenable {

    /**
     * Indique s'il est possible de dessiner une forme
     *
     * @return true s'il est possible de dessiner une forme.
     */
    boolean canDraw();

    /**
     * Indique si la forme possède un rayon
     *
     * @return true si la forme possède un rayon
     */
    boolean hasRadius();

    /**
     *
     * @return color la couleur du crayon
     */
    Color getColor();

    /**
     *
     * @return Le point de départ du crayon
     */
    Point2D getStartPoint();

    /**
     *
     * @return Le point d'arrivé du crayon
     */
    Point2D getEndPoint();

    /**
     *
     * @return le nom de l'élément graphique
     */
    String getGraphicElementName();

    /**
     *
     * @return le type de l'élément graphique
     */
    String getGraphicElementType();

    /**
     * Renvoie la distance entre le point de départ et d'arrivé.
     *
     * Renvoie 0.0 si un deux deux points vaut null.
     *
     * @return la distance entre le point de départ et d'arrivé.
     */
    double getDistance();

    /**
     * Met à jour la couleur.
     *
     * @param color à mettre à jour, ne doit pas être null
     */
    void setColor(Color color);

    /**
     * Met à jour le nom de l'élément graphique.
     *
     * @param name à mettre à jour, ne doit pas être null
     */
    void setGraphicElementName(String name);

    /**
     * Met à jour le type de l'élément graphique.
     *
     * @param type à mettre à jour, ne doit pas être null
     */
    void setGraphicElementType(String type);

    /**
     * Met à jour le point de départ.
     *
     * @param point à mettre à jour
     */
    void setStartPoint(Point2D point);

    /**
     * Met à jour le point d'arrivé.
     *
     * @param point à mettre à jour
     */
    void setEndPoint(Point2D point);
}
