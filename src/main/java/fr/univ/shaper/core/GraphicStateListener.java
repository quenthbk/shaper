package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicElement;

import java.util.EventListener;

public interface GraphicStateListener extends EventListener {
    void event(GraphicElement element);
}
