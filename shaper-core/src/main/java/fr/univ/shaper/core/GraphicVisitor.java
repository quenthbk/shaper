package fr.univ.shaper.core;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

/**
 * Interface permettant d'implémenter des visiteurs accepté par les éléments graphiques
 */
public interface GraphicVisitor {

    /**
     * Visite un calque
     *
     * @param element la calque à visiter
     */
    void visitLayer(Layer element);

    /**
     * Visite un rectangle de type Perfect
     *
     * @param element le rectangle parfait à visiter
     */
    void visitPerfectRectangle(PerfectRectangle element);

    /**
     * Visite un cercle de type Perfect
     *
     * @param element le cercle perfect à visiter
     */
    void visitPerfectCircle(PerfectCircle element);

    /**
     * Visite une ligne de type Perfect
     *
     * @param element la ligne perfect à visiter
     */
    void visitPerfectLine(PerfectLine element);

    /**
     * Visite un rectangle de type Noisy
     *
     * @param element le rectangle noisy à visiter
     */
    void visitNoisyRectangle(NoisyRectangle element);

    /**
     * Visite un cercle de type Noisy
     *
     * @param element le cercle noisy à visiter
     */
    void visitNoisyCircle(NoisyCircle element);

    /**
     * Visite une ligne de type Noisy
     *
     * @param element la ligne noisy à visiter
     */
    void visitNoisyLine(NoisyLine element);
}