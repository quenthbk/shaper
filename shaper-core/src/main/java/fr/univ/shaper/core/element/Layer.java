package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.exception.IllegalParentException;
import fr.univ.shaper.util.Contract;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Objects;

/**
 * La classe représentant un calque d'élément graphique.
 */
public class Layer implements GraphicElement {

    /**
     * Une liste des enfants du calque
     */
    private final List<GraphicElement> graphicElements;

    /**
     * Le parent de ce calque
     */
    private Layer parent;

    /**
     * La longueur du calque
     */
    private int width;

    /**
     * La hauteur du calque
     */
    private int height;

    public Layer() {
        graphicElements = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Ajoute un enfant au calque et set le parent de cet élément avec lui-même.
     *      Ne modifie rien sinon.
     *
     * @param graphicElement l'enfant à ajouter. Ne doit pas être null.
     */
    public void append(GraphicElement graphicElement) {
        Contract.assertThat(graphicElement != null, "L'élement ne doit pas être null");
        graphicElement.setParent(this);
        graphicElements.add(graphicElement);
    }

    /**
     * Supprime un enfant du calque si celui-ci est présent et, set le parent de cet élément à null.
     *  Ne modifie rien sinon.
     *
     * @param graphicElement l'élement à supprimer, ne doit pas être null.
     */
    public void remove(GraphicElement graphicElement) {
        Contract.assertThat(graphicElement != null, "L'élement ne doit pas être null");
        if (graphicElements.remove(graphicElement)) {
            graphicElement.setParent(null);
        }
    }

    /**
     * Renvoie l'enfant du calque positionné à la position {i}
     *
     * @param i la position de l'enfant
     * @return l'enfant du calque positionné à la position {i}
     */
    public GraphicElement getChild(int i) {
        return graphicElements.get(i);
    }

    /**
     * Renvoie la liste de tous les enfants du calque
     *
     * @return la liste de tous les enfants du calque
     */
    public Collection<GraphicElement> getChildren() {
        return graphicElements;
    }

    /**
     * @return la longueur du calque
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return la hauteur du calque
     */
    public int getHeight() {
        return height;
    }

    /**
     * Modifie la longueur du calque
     *
     * @param width la nouvelle longueur du calque
     */
    public void setWidth(int width) {
        Contract.assertThat(width > 0, "La longueur doit être supérieur à 0");
        this.width = width;
    }

    /**
     * Modifie la hauteur du calque
     *
     * @param height la nouvelle hauteur du calque
     */
    public void setHeight(int height) {
        Contract.assertThat(height > 0, "La hauteur doit être supérieur à 0");
        this.height = height;
    }

    @Override
    public Layer getParent() {
        return parent;
    }

    @Override
    public void setParent(Layer element) {
        GraphicElement parentVerification = parent;
        while (parentVerification != null) {
            if (this == parentVerification) {
                throw new IllegalParentException("Un sous calque ne peut pas être lui même un super calque.");
            }
            parentVerification = parentVerification.getParent();
        }
        parent = element;
    }

    @Override
    public void accept(GraphicVisitor visitor) {
        visitor.visitLayer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Layer)) return false;
        Layer layer = (Layer) o;
        return graphicElements.equals(layer.graphicElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphicElements);
    }

    @Override
    public String toString() {
        return "Layer{" +
                "graphicElements=" + graphicElements +
                '}';
    }
}
