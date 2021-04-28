package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.exception.IllegalParentException;

/**
 * Interface représentant un élément Graphique
 */
public interface GraphicElement {


    /**
     * Renvoie le parent de l'élément graphique, null s'il n'en posséde pas.
     *
     * @return le parent de l'élément graphique.
     */
    Layer getParent();

    /**
     * L'élément graphique accepte un visiteur
     *
     * @param visitor le visteur à accepter
     */
    void accept(GraphicVisitor visitor);

    /**
     * Met à jour le parent
     *
     * @param element le parent de l'élément graphique
     * @throws IllegalParentException si le parent n'est pas accepté
     */
    void setParent(Layer element);
}
