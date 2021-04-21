package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicElement;

import java.io.File;

public interface Director {
    GraphicElement load(File file);

    void save(GraphicElement element);

    void saveAs(File file, GraphicElement element);
}
