package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.exception.IllegalParentException;

import java.util.*;

public class Layer implements GraphicElement {

    private final List<GraphicElement> graphicElements;

    private Layer parent;

    private int width;

    private int height;

    public Layer() {
        graphicElements = Collections.synchronizedList(new ArrayList<>());
    }

    public void append(GraphicElement graphicElement) {
        graphicElements.add(graphicElement);
    }

    public void remove(GraphicElement graphicElement) {
        graphicElements.remove(graphicElement);
    }

    public GraphicElement getChild(int i) {
        return graphicElements.get(i);
    }

    public Collection<GraphicElement> getChildren() {
        return graphicElements;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
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
                throw new IllegalParentException("Un fils ne peut pas être son père.");
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
