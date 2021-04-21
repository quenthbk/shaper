package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.element.Layer;

import java.io.File;

public interface Director {
    Layer load(File file, GraphicBuilder builder);

    void save(Layer element);

    void saveAs(File file, Layer element);

    boolean fileIsPresent();

    File getFile();
}
