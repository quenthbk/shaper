package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;

import java.io.File;
import java.io.IOException;

public interface Director {
    Layer load(File file, GraphicBuilder builder) throws IOException;

    void save(Layer element) throws IOException;

    void saveAs(File file, Layer element) throws IOException;

    boolean fileIsPresent();

    File getFile();
}
