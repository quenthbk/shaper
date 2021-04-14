package fr.univ.shaper.graphic;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DefaultGraphicBuilder implements GraphicBuilder {

    private GraphicFactory factory;
    private GraphicContext context;
    private final List<Point> points;

    public DefaultGraphicBuilder(GraphicFactory factory) {
        setGraphicFactory(factory);
        points = new ArrayList<>(2);
    }

    @Override
    public void setGraphicFactory(@NotNull GraphicFactory factory) {
        this.factory = factory;
    }

    @Override
    public void startContext(@NotNull GraphicContext context) {
        this.context = context;
        this.points.clear();
    }

    @Override
    public void appendPoint(@NotNull Point point) {
        points.add(point);
    }

    @Override
    public GraphicElement build() throws BadGraphicContextException {
        if (context == null) {
            throw new BadGraphicContextException("Context have to be set");
        }

        // TODO Doit être traité différemment des autres !
        if (context == GraphicContext.LAYER) {
            return factory.createLayer();
        }

        // Vérification des éléments à disposition avant la création d'un Shape
        if (points.size() < 2) {
            throw new BadGraphicContextException("Context " + context.name() + " must have two points added");
        }

        Point p0 = points.get(0);
        Point p1 = points.get(1);

        switch (context) {
            case LINE:
                return factory.createLine(p0.getX(), p0.getY(), p1.getX(), p1.getY(), null);
            case RECTANGLE:
                return factory.createRectangle(p0.getX(), p0.getY(), p1.getX(), p1.getY(), null);
            case CIRCLE:
                double radius = Math.abs(p0.getX() - p1.getX());
                return factory.createCircle(p0.getX(), p0.getY(), radius, null);
        }
        throw new BadGraphicContextException("Context " + context.name() + " not supported by this builder");
    }
}
