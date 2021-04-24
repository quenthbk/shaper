package fr.univ.shaper.core.util;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

import java.io.PrintStream;

public class PrintGraphicVisitor implements GraphicVisitor {

    private final PrintStream out;

    public PrintGraphicVisitor(PrintStream out) {
        this.out = out;
    }

    public PrintGraphicVisitor() {
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
