package fr.univ.shaper.graphic.element;

import fr.univ.shaper.graphic.GraphicElement;
import fr.univ.shaper.graphic.GraphicVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class Layer implements GraphicElement {

    private final List<GraphicElement> graphicElements;

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
