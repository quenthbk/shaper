package fr.univ.shaper.converter;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

import java.util.Stack;

public class AllShapeToPerfectVisitor implements GraphicVisitor {

    public Stack<Layer> stack;

    public AllShapeToPerfectVisitor() {
        stack = new Stack<>();
        stack.push(new Layer());
    }

    @Override
    public void visitLayer(Layer element) {
        stack.peek().append(element);
        stack.push(element);
        element.getChildren().forEach(e -> e.accept(this));
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        stack.peek().append(element);
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        stack.peek().append(element);
    }

    @Override
    public void visitPerfectLine(PerfectLine element) {
        stack.peek().append(element);
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {

    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {

    }

    @Override
    public void visitNoisyLine(NoisyLine element) {

    }

    public GraphicElement getResult() {
        return stack.pop();
    }
}
