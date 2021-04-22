package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.model.PencilImpl;
import fr.univ.shaper.util.Contract;

import java.awt.*;

public class BuildElementCommand implements Command {

    private final GraphicBuilder builder;

    public BuildElementCommand(GraphicBuilder builder) {
        Contract.assertThat(builder != null, "Le builder ne peut pas être null");
        this.builder = builder;
    }

    @Override
    public void runCommand(DrawingBoard controller) {
        PencilImpl pencil = controller.getPencil();
        Contract.assertThat(pencil.getEndPoint() != null,
                "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.canDraw(), "Le crayon n'est pas en train de dessiner.");

        Point firstPoint = new Point(
                Math.min(pencil.getStartPoint().getX(), pencil.getEndPoint().getX()),
                Math.min(pencil.getStartPoint().getY(), pencil.getEndPoint().getY()));

        builder.setGraphicName(pencil.getGraphicElementName())
                .setGraphicType(pencil.getGraphicElementType())
                .setGraphicAttribute("color", pencil.getColor(), Color.class)
                .appendPoint(firstPoint);

        if (pencil.hasRadius()) {
            builder.setGraphicAttribute("radius", pencil.getDistance(), double.class);
        } else {
            Point lastPoint = new Point(
                    Math.max(pencil.getStartPoint().getX(), pencil.getEndPoint().getX()),
                    Math.max(pencil.getStartPoint().getY(), pencil.getEndPoint().getY()));
            builder.appendPoint(lastPoint);
        }

        GraphicElement element = null;

        try {
            element = builder.build();
        } catch (BadGraphicContextException e) {
            e.printStackTrace();
        }

        controller.setSelectedElement(element);
    }
}
