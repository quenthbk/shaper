package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;

/**
 * Le constructeur d'élément graphique
 */
public interface GraphicBuilder {

    /**
     * Reset les informations de construction d'un objet à null
     *
     * @return lui même
     */
    GraphicBuilder reset();

    /**
     * Set le nom de l'élément graphique
     *
     * @param name le nom de l'élément graphique
     * @return lui même
     */
    GraphicBuilder setGraphicName(String name);

    /**
     * Set le type de l'élément graphique
     *
     * @param type le type de l'élément graphique
     * @return lui même
     */
    GraphicBuilder setGraphicType(String type);

    /**
     * Set un attribut de l'élément graphique
     *
     * @param key le nom de l'attribut
     * @param value la valeur de l'attribut
     * @param clazz le type de la valeur
     * @return lui même
     */
    <T> GraphicBuilder setGraphicAttribute(String key, T value, Class<T> clazz);

    /**
     * Ajoute un point à l'élément graphique
     *
     * @param point un point
     * @return lui même
     */
    GraphicBuilder appendPoint(Point point);

    /**
     * Construit l'élément graphique
     *
     * @return l'élément graphique construit
     * @throws BadGraphicContextException si le context de construction est incorect.
     */
    GraphicElement build() throws BadGraphicContextException;
}
