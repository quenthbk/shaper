package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.shape.Circle;
import fr.univ.shaper.graphic.shape.Line;
import fr.univ.shaper.graphic.shape.Rectangle;

public interface GraphicVisitor {
    void visitRectangle(Rectangle element);

    void visitCircle(Circle element);

    void visitLine(Line element);
}