package fr.univ.shaper.visitor;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import fr.univ.shaper.graphic.GraphicElement;
import fr.univ.shaper.graphic.GraphicVisitor;
import fr.univ.shaper.graphic.Layer;
import fr.univ.shaper.graphic.shape.*;
import fr.univ.shaper.graphic.shape.Rectangle;

public class DefaultGraphicVisitor implements GraphicVisitor {
    private final Graphics2D screen;

    public DefaultGraphicVisitor(Graphics2D screen) {
        this.screen = screen;
    }

    public void visitRectangle(Rectangle rect) {
        screen.setColor(rect.getColor());
        screen.draw(new Rectangle2D.Double(rect.getX0(), rect.getY0()
                , rect.getX1()- rect.getX0(), rect.getY1()-rect.getY0()));
    }

    public void visitCircle(Circle circle) {
        screen.setColor(circle.getColor());
        screen.draw(new Ellipse2D.Double(circle.getCx()-circle.getRadius(),
                circle.getCy()-circle.getRadius(),
                circle.getRadius()*2,
                circle.getRadius()*2));
    }

    public void visitLine(Line line) {
        screen.setColor(line.getColor());
        screen.draw(new Line2D.Double(line.getX0(), line.getY0(), line.getX1(), line.getY1()));

    }

    public void visitLayer(Layer layer) {
        for(GraphicElement ge : layer.getElements()) {
            ge.accept(this);
        }
    }
}
