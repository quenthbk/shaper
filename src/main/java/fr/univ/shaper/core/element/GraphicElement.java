package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicVisitor;

public interface GraphicElement {
    /**
     * Renvoie le parent de l'élément graphique.
     *
     * @return le parent de l'élément graphique.
     */
    Layer getParent();

    /**
     * Met à jour le parent de l'élément graphique
     *
     * @param element le parent de l'élément graphique
     */
    void setParent(Layer element);

    /**
     * L'élément graphique accepte un visiteur
     *
     * @param visitor le visteur à accepter
     */
    void accept(GraphicVisitor visitor);
}
