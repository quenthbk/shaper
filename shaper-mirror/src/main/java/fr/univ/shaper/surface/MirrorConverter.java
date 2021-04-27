package fr.univ.shaper.surface;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;

import java.io.File;
import java.io.IOException;

public class MirrorConverter {

    private final Director director;

    private final GraphicBuilder builder;

    public MirrorConverter(Director director, GraphicBuilder builder) {
        this.director = director;
        this.builder = builder;
    }

    public void convert(File src, File dest) throws IOException {
        Layer layer = director.load(src, builder);
        MirrorVisitor visitor = new MirrorVisitor();
        layer.accept(visitor);
        director.saveAs(dest, layer);
    }
}
