package fr.univ.shaper.visitor;

import fr.univ.shaper.graphic.GraphicVisitor;
import fr.univ.shaper.graphic.element.Layer;
import fr.univ.shaper.graphic.element.noisy.NoisyCircle;
import fr.univ.shaper.graphic.element.noisy.NoisyLine;
import fr.univ.shaper.graphic.element.noisy.NoisyRectangle;
import fr.univ.shaper.graphic.element.perfect.PerfectCircle;
import fr.univ.shaper.graphic.element.perfect.PerfectLine;
import fr.univ.shaper.graphic.element.perfect.PerfectRectangle;

import java.io.PrintStream;

public class PrintGraphicVisitor implements GraphicVisitor {

    private final PrintStream out;

    PrintGraphicVisitor(PrintStream out) {
        this.out = out;
    }

    PrintGraphicVisitor() {
        this.out = System.out;
    }

    @Override
    public void visitLayer(Layer element) {
        out.println(element);
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        out.println(element);
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        out.println();
    }

    @Override
    public void visitPerfectLine(PerfectLine element) {
        out.println();
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        out.println();
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        out.println();
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        out.println();
    }
}
