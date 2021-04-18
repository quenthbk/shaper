package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.element.Point;
import fr.univ.shaper.graphic.exception.BadGraphicContextException;

public interface GraphicBuilder {
    GraphicBuilder reset();

    GraphicBuilder setGraphicName(String name);

    GraphicBuilder setGraphicType(String type);

    <T> GraphicBuilder setGraphicAttribute(String key, T value, Class<T> clazz);

    GraphicBuilder appendPoint(Point point);

    GraphicElement build() throws BadGraphicContextException;
}
