package fr.univ.shaper.core;

public interface GraphicElement {
    void accept(GraphicVisitor visitor);
}
