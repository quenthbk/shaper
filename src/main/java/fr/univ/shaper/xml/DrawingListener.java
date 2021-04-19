package fr.univ.shaper.xml;

import fr.univ.shaper.graphic.element.Layer;

import java.util.EventListener;

public interface DrawingListener extends EventListener {
    void event(Layer result);
}
