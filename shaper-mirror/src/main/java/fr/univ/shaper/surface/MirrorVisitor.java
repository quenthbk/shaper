package fr.univ.shaper.surface;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.*;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

public class MirrorVisitor implements GraphicVisitor {

    public Layer root;

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
        double width = rectangle.getWidth();
        rectangle.setX0(root.getWidth() - rectangle.getX0() - width);
        rectangle.setX1(root.getWidth() - rectangle.getX1() + width);
    }

    private void visitCircle(Circle circle) {
        circle.setCx(root.getWidth() - circle.getCx());
    }

    private void visitLine(Line line) {
        line.setX0(root.getWidth() - line.getX0());
        line.setX1(root.getWidth() - line.getX1());
    }
}
