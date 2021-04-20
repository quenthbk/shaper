package fr.univ.shaper.visitor;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

public class DefaultGraphicVisitor implements GraphicVisitor {
    private Graphics2D screen;

    public DefaultGraphicVisitor(Graphics2D screen) {
        this.screen = screen;
    }

    public void setGraphics(Graphics2D screen) {
        this.screen = screen;
    }

    public void visitPerfectRectangle(PerfectRectangle rect) {
        screen.setColor(rect.getColor());
        screen.draw(new Rectangle2D.Double(rect.getX0(), rect.getY0()
                , rect.getX1()- rect.getX0(), rect.getY1()-rect.getY0()));
    }

    public void visitPerfectCircle(PerfectCircle circle) {
        screen.setColor(circle.getColor());
        screen.draw(new Ellipse2D.Double(circle.getCx()-circle.getRadius(),
                circle.getCy()-circle.getRadius(),
                circle.getRadius()*2,
                circle.getRadius()*2));
    }

    public void visitPerfectLine(PerfectLine line) {
        screen.setColor(line.getColor());
        screen.draw(new Line2D.Double(line.getX0(), line.getY0(), line.getX1(), line.getY1()));
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        Color c = element.getColor();
        double x0, x1, y0, y1, noiseX, noiseY;
        x0 = element.getX0();
        y0 = element.getY0();
        x1 = element.getX1();
        y1 = element.getY1();
        noiseX = element.getNoiseX();
        noiseY = element.getNoiseY();

        new NoisyLine(x0, y0, x1, y0, c, noiseX, noiseY).accept(this);
        new NoisyLine(x0, y0, x0, y1, c, noiseX, noiseY).accept(this);
        new NoisyLine(x0, y1, x1, y1, c, noiseX, noiseY).accept(this);
        new NoisyLine(x1, y0, x1, y1, c, noiseX, noiseY).accept(this);
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        screen.setColor(element.getColor());
        double cx, cy, rad, noiseX, noiseY;
        cx = element.getCx();
        cy = element.getCy();
        rad = element.getRadius();
        noiseX = element.getNoiseX();
        noiseY = element.getNoiseY();

        screen.draw(new Ellipse2D.Double(
                cx-rad,
                cy-rad,
                rad*2 + noiseX,
                rad*2 + noiseY));
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        screen.setColor(element.getColor());

        double x0, y0, x1, y1, noiseX, noiseY;
        x0 = element.getX0();
        y0 = element.getY0();
        x1 = element.getX1();
        y1 = element.getY1();
        noiseX = element.getNoiseX();
        noiseY = element.getNoiseY();

        screen.draw(new QuadCurve2D.Double(x0, y0,
                (x1 + x0) / 2 + noiseX,
                (y1 + y0) / 2 + noiseY,
                x1, y1));
    }

    public void visitLayer(Layer layer) {
        for(GraphicElement ge : layer.getChildren()) {
            ge.accept(this);
        }
    }
}
