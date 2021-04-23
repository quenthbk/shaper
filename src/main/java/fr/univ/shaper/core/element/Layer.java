package fr.univ.shaper.core.element;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicVisitor;

import java.util.*;

public final class Layer implements GraphicElement {

    private final List<GraphicElement> graphicElements;

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
