package fr.univ.shaper.surface;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;

import java.io.File;
import java.io.IOException;

public class SurfaceCalculate {

    private final Director director;

    private final GraphicBuilder builder;

    public SurfaceCalculate(Director director, GraphicBuilder builder) {
        this.director = director;
        this.builder = builder;
    }

    public void calcul(File src) throws IOException {
        Layer layer = director.load(src, builder);
        SurfaceVisitor visitor = new SurfaceVisitor();
        layer.accept(visitor);
        System.out.println("La surface total du dessin est de " + visitor.getResult() + " pixels");
    }
}
