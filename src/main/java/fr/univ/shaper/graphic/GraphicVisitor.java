package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.shape.*;

public interface GraphicVisitor {
    void visitLayer(Layer element);

    void visitPerfectRectangle(PerfectRectangle element);

    void visitPerfectCircle(PerfectCircle element);

    void visitPerfectLine(PerfectLine element);

    void visitNoisyRectangle(NoisyRectangle element);

    void visitNoisyCircle(NoisyCircle element);

    void visitNoisyLine(NoisyLine element);
}