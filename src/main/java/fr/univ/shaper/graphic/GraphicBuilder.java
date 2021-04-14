package fr.univ.shaper.graphic;

public interface GraphicBuilder {
    GraphicBuilder setGraphicFactory(GraphicFactory factory);

    GraphicBuilder startContext(GraphicContext context);

    GraphicBuilder appendPoint(Point point);

    GraphicElement build() throws BadGraphicContextException;
}
