package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicStateListener;

public interface Director {
    void load(String filename, GraphicStateListener listener);

    void save(String filename, GraphicElement element);
}
