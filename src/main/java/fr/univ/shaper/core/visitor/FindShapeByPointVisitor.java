package fr.univ.shaper.core.visitor;

import fr.univ.shaper.core.FilterGraphicVisitor;
import fr.univ.shaper.core.element.*;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;
import fr.univ.shaper.util.Contract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FindShapeByPointVisitor implements FilterGraphicVisitor {

    private Point point;

    private final List<GraphicElement> elements;

    public FindShapeByPointVisitor(Point point)  {
        Contract.assertThat(point != null, "Le point ne doit pas être null");
        this.point = point;
        elements = new ArrayList<>();
    }

    public void setPoint(Point point) {
        Contract.assertThat(point != null, "Le point ne doit pas être null");
        this.point = point;
        elements.clear();
    }

    @Override
    public Collection<GraphicElement> getResult() {
        return elements;
    }

    @Override
    public void visitLayer(Layer element) {
        element.getChildren().forEach(e -> e.accept(this));
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        if (pointSelectRectangle(element)) {
            elements.add(element);
        }
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        if (pointSelectCircle(element)) {
            elements.add(element);
        }
    }

    @Override
    public void visitPerfectLine(PerfectLine element) {
        if (pointSelectLine(element)) {
            elements.add(element);
        }
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        if (pointSelectRectangle(element)) {
            elements.add(element);
        }
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        if (pointSelectCircle(element)) {
            elements.add(element);
        }
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        if (pointSelectLine(element)) {
            elements.add(element);
        }
    }

    private boolean pointSelectRectangle(Rectangle rectangle) {
        return rectangle.getX0() < point.getX() &&
                rectangle.getX1() > point.getX() &&
                rectangle.getY0() < point.getY() &&
                rectangle.getY1() > point.getX();
    }

    private boolean pointSelectCircle(Circle circle) {
        return (point.getX() - circle.getCx()) * (point.getX() - circle.getCx())
                + (point.getY() - circle.getCy()) * (point.getY() - circle.getCy())
                < circle.getRadius() * 2;
    }

    private boolean pointSelectLine(Line line) {
        return line.getX0() < point.getX() &&
                line.getX1() > point.getX() &&
                line.getY0() < point.getY() &&
                line.getY1() > point.getX();
    }
}
