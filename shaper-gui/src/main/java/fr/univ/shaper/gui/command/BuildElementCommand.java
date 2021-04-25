package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.DrawingConstants;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.model.Pencil;
import fr.univ.shaper.util.Contract;

import java.awt.*;

public class BuildElementCommand implements Command {

    private final GraphicBuilder builder;

    public BuildElementCommand(GraphicBuilder builder) {
        Contract.assertThat(builder != null, "Le builder ne peut pas être null");
        this.builder = builder;
    }

    @Override
    public void runCommand(DrawingBoard controller) throws UnperformedCommandException {
        Pencil pencil = controller.getPencil();
        Contract.assertThat(pencil.getEndPoint() != null,
                "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.canDraw(), "Le crayon n'a pas les éléments necessaire pour " +
                "finaliser le dessin. : " + pencil);

        Point firstPoint;
        Point lastPoint;
        // Calcul des points pour correctement dessiner un rectangle.
        if (DrawingConstants.RECTANGLE.equals(pencil.getGraphicElementName())) {
            firstPoint = new Point(
                    Math.min(pencil.getStartPoint().getX(), pencil.getEndPoint().getX()),
                    Math.min(pencil.getStartPoint().getY(), pencil.getEndPoint().getY()));
            lastPoint = new Point(
                    Math.max(pencil.getStartPoint().getX(), pencil.getEndPoint().getX()),
                    Math.max(pencil.getStartPoint().getY(), pencil.getEndPoint().getY()));
        } else {
            firstPoint = new Point(pencil.getStartPoint().getX(), pencil.getStartPoint().getY());
            lastPoint = new Point(pencil.getEndPoint().getX(), pencil.getEndPoint().getY());
        }

        builder.setGraphicName(pencil.getGraphicElementName())
                .setGraphicType(pencil.getGraphicElementType())
                .setGraphicAttribute("color", pencil.getColor(), Color.class)
                .appendPoint(firstPoint);

        if (pencil.hasRadius()) {
            builder.setGraphicAttribute("radius", pencil.getDistance(), double.class);
        } else {
            builder.appendPoint(lastPoint);
        }

        GraphicElement element = null;

        try {
            element = builder.build();
        } catch (BadGraphicContextException e) {
            throw new UnperformedCommandException("Impossible de construire l'élément.", e);
        }

        controller.setSelectedElement(element);
    }
}
