package fr.univ.shaper.graphic;

import java.util.ArrayList;
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

    synchronized public GraphicElement get(int i) {
        return graphicElements.get(i);
    }

    @Override
    synchronized public void accept(GraphicVisitor visitor) {
        graphicElements.forEach((e) -> {
            e.accept(visitor);
        });
    }
}
