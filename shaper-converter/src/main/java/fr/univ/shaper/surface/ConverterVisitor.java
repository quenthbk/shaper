package fr.univ.shaper.surface;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.*;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;
import fr.univ.shaper.util.Contract;

import java.util.Stack;

public class ConverterVisitor implements GraphicVisitor {

    public Stack<Layer> src;

    public Stack<Layer> dest;

    public GraphicFactory factory;

    public ConverterVisitor(GraphicFactory factory) {
        Contract.assertThat(factory != null, "La factory ne doit pas être null");
        src = new Stack<>();
        src.push(new Layer());
        dest = new Stack<>();
        dest.push(new Layer());
        this.factory = factory;
    }

    @Override
    public void visitLayer(Layer element) {
        Layer layer = factory.createLayer();
        dest.peek().append(layer);
        dest.push(layer);
        element.getChildren().forEach(e -> e.accept(this));
        dest.pop();
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

    public Layer getResult() {
        return dest.pop();
    }

    private void visitRectangle(Rectangle rectangle) {
        dest.peek().append(factory.createRectangle(
                rectangle.getX0(),
                rectangle.getY0(),
                rectangle.getX1(),
                rectangle.getY1(),
                rectangle.getColor()
        ));
    }

    private void visitCircle(Circle circle) {
        dest.peek().append(factory.createCircle(
                circle.getCx(),
                circle.getCy(),
                circle.getRadius(),
                circle.getColor()
        ));
    }

    private void visitLine(Line line) {
        dest.peek().append(factory.createLine(
                line.getX0(),
                line.getY0(),
                line.getX1(),
                line.getY1(),
                line.getColor()
        ));
    }
}
