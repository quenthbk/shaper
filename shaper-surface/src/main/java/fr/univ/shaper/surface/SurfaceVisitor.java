package fr.univ.shaper.surface;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.*;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

public class SurfaceVisitor implements GraphicVisitor {

    public Layer root;

    public double result;

    public SurfaceVisitor() {
        this.result = 0;
    }

    @Override
    public void visitLayer(Layer element) {
        if (root == null) {
            root = element;
        }
        element.getChildren().forEach(e -> e.accept(this));
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        visitRectangle(element);
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        visitCircle(element);
    }

    @Override
    public void visitPerfectLine(PerfectLine element) {
        visitLine(element);
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        visitRectangle(element);
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        visitCircle(element);
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        visitLine(element);
    }

    private void visitRectangle(Rectangle rectangle) {

        setResult(getResult() + rectangle.getWidth() * rectangle.getHeight());
    }

    private void visitCircle(Circle circle) {
        setResult(getResult() + Math.pow(circle.getRadius(), 2) * Math.PI);
    }

    private void visitLine(Line line) {

    }

    public double getResult() {
        return result;
    }

    private void setResult(double surface) {
        this.result = surface;
    }
}
