package fr.univ.shaper.core;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

/**
 * Cette classe ne fait rien du tout mais permet d'éviter l'implémentation
 *      successive lors d'un héritage
 */
public class GraphicVisitorAdapter implements GraphicVisitor {
    @Override
    public void visitLayer(Layer element) {

    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {

    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {

    }

    @Override
    public void visitPerfectLine(PerfectLine element) {

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
}
