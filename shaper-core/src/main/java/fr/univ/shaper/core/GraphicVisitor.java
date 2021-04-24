package fr.univ.shaper.core;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

public interface GraphicVisitor {
    void visitLayer(Layer element);

    void visitPerfectRectangle(PerfectRectangle element);

    void visitPerfectCircle(PerfectCircle element);

    void visitPerfectLine(PerfectLine element);

    void visitNoisyRectangle(NoisyRectangle element);

    void visitNoisyCircle(NoisyCircle element);

    void visitNoisyLine(NoisyLine element);
}