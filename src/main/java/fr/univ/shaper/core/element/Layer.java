package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.exception.IllegalParentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Layer implements GraphicElement {

    private final List<GraphicElement> graphicElements;

    private Layer parent;

    public Layer() {
        graphicElements = new ArrayList<>();
    }

    synchronized public void append(GraphicElement graphicElement) {
        graphicElements.add(graphicElement);
    }

    synchronized public void remove(GraphicElement graphicElement) {
        graphicElements.remove(graphicElement);
    }

    synchronized public GraphicElement getChild(int i) {
        return graphicElements.get(i);
    }

    synchronized public Collection<GraphicElement> getChildren() {
        return new ArrayList<GraphicElement>(graphicElements);
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
                throw new IllegalParentException("Un fils ne peut pas être son père.");
            }
            parentVerification = parentVerification.getParent();
        }
        parent = element;
    }

    @Override
    synchronized public void accept(GraphicVisitor visitor) {
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
