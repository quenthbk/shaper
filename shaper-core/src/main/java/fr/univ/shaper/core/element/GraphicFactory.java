package fr.univ.shaper.core.element;

import java.awt.Color;

/**
 * Interface contenant les méthodes à implémenter pour créer un élément graphique.
 */
public interface GraphicFactory {
    /**
     * Instancie un calque.
     *
     * @return Un nouveau calque
     */
    Layer createLayer();

    /**
     * Instancie un rectangle
     *
     * @param x0 la coordonnée x du point de début du rectangle
     * @param y0 la coordonnée y du point de début du rectangle
     * @param x1 la coordonnée x du point de fin du rectangle
     * @param y1 la coordonnée y du point de fin du rectangle
     * @param color la couleur du rectangle
     * @return Un nouveau rectangle dont les paramètres de celui-ci sont ceux mentionnés dans les paramètres de la
     *      méthode
     */
    Rectangle createRectangle(double x0, double y0, double x1, double y1, Color color);


    /**
     * Instancie un cercle
     *
     * @param cx la coordonnée x du centre du cercle
     * @param cy la coordonnée y du centre du cercle
     * @param radius le rayon du cercle
     * @param color la couleur de la ligne
     * @return Un nouveau cercle dont les paramètres de celui-ci sont ceux mentionnés dans les paramètres de la
     *      méthode
     */
    Circle createCircle(double cx, double cy, double radius, Color color);

    /**
     * Instancie une ligne
     *
     * @param x0 la coordonnée x du point de début de la ligne
     * @param y0 la coordonnée y du point de début de la ligne
     * @param x1 la coordonnée x du point de fin de la ligne
     * @param y1 la coordonnée y du point de fin de la ligne
     * @param color la couleur de la ligne
     * @return Une nouvelle ligne dont les paramètres de celui-ci sont ceux mentionnés dans les paramètres de la
     *      méthode
     */
    Line createLine(double x0, double y0, double x1, double y1, Color color);
}
