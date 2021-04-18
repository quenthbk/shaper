package fr.univ.shaper.graphic;

import fr.univ.shaper.graphic.element.Layer;
import fr.univ.shaper.graphic.element.noisy.NoisyCircle;
import fr.univ.shaper.graphic.element.noisy.NoisyLine;
import fr.univ.shaper.graphic.element.noisy.NoisyRectangle;
import fr.univ.shaper.graphic.element.perfect.PerfectCircle;
import fr.univ.shaper.graphic.element.perfect.PerfectLine;
import fr.univ.shaper.graphic.element.perfect.PerfectRectangle;

public interface GraphicVisitor {
    void visitLayer(Layer element);

    void visitPerfectRectangle(PerfectRectangle element);

    void visitPerfectCircle(PerfectCircle element);

    void visitPerfectLine(PerfectLine element);

    void visitNoisyRectangle(NoisyRectangle element);

    void visitNoisyCircle(NoisyCircle element);

    void visitNoisyLine(NoisyLine element);
}