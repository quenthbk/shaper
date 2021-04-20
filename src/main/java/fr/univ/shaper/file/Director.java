package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicElement;

public interface Director {
    GraphicElement load(String filename);

    void save(GraphicElement element);

    void saveAs(String filename, GraphicElement element);
}
