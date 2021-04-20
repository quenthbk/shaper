package fr.univ.shaper.xml;

import fr.univ.shaper.core.GraphicElement;
import java.util.EventListener;

public interface DrawingListener extends EventListener {
    void event(GraphicElement element);
}
