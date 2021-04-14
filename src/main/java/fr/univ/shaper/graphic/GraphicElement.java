package fr.univ.shaper.graphic;

public interface GraphicElement {
    void accept(GraphicVisitor visitor);
}
