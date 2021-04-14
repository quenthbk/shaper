package fr.univ.shaper.graphic;

public interface GraphicBuilder {
    void setGraphicFactory(GraphicFactory factory);

    void startContext(GraphicContext context);

    void appendPoint(Point point);

    GraphicElement build() throws BadGraphicContextException;
}
