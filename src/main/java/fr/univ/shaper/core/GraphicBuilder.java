package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;

public interface GraphicBuilder {
    GraphicBuilder reset();

    GraphicBuilder setGraphicName(String name);

    GraphicBuilder setGraphicType(String type);

    <T> GraphicBuilder setGraphicAttribute(String key, T value, Class<T> clazz);

    GraphicBuilder appendPoint(Point point);

    GraphicElement build() throws BadGraphicContextException;
}
