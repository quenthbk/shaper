package fr.univ.shaper.gui.command;

import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

import java.awt.geom.Point2D;

/**
 * Commencer à dessiner un élément graphique
 */
public class StartDrawingCommand implements Command {

    private Point2D point;

    public StartDrawingCommand(Point2D point) {
        Contract.assertThat(point != null, "Le point ne doit pas être null");
        this.point = point;
    }

    public void setPoint(Point2D point) {
        Contract.assertThat(point != null, "Le point ne doit pas être null");
        this.point = point;
    }

    @Override
    public void runCommand(DrawingBoard controller) throws UnperformedCommandException {
        controller.getPencil().setStartPoint(point);
    }
}
