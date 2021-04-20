package fr.univ.shaper.core;

import java.util.EventListener;

public interface GraphicStateListener extends EventListener {
    void event(GraphicElement element);
}
