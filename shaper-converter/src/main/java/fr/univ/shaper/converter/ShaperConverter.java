package fr.univ.shaper.converter;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;

import java.io.File;
import java.io.IOException;

public class ShaperConverter {

    private final Director director;

    private final GraphicFactory factory;

    private final GraphicBuilder builder;

    public ShaperConverter(Director director, GraphicFactory factory, GraphicBuilder builder) {
        this.director = director;
        this.factory = factory;
        this.builder = builder;
    }

    public void convert(File src, File dest) throws IOException {
        Layer layer = director.load(src, builder);
        ConverterVisitor visitor = new ConverterVisitor(factory);
        layer.getChildren().forEach(e -> e.accept(visitor));
        director.saveAs(dest, visitor.getResult());
    }
}
