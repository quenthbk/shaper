package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicElement;

import java.util.Collection;

public interface FilterGraphicVisitor extends GraphicVisitor {
    Collection<GraphicElement> getResult();
}
