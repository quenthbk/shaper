package fr.univ.shaper.graphic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Layer implements GraphicElement {

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
}
