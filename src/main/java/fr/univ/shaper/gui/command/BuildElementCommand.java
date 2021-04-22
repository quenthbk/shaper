package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
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
    public void runCommand(DrawingBoard controller) {
        Pencil pencil = controller.getPencil();
        Contract.assertThat(pencil.getEndPoint() != null,
                "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.isDrawing(), "Le crayon n'est pas en train de dessiner." +
                "Impossible de d'effectuer un rendu...");

        builder.setGraphicName(pencil.getShapeName())
                .setGraphicType(pencil.getShapeType())
                .setGraphicAttribute("color", pencil.getColor(), Color.class)
                .appendPoint(new fr.univ.shaper.core.element.Point(
                                pencil.getStartPoint().getX(),
                                pencil.getStartPoint().getY()
                        )
                );

        if (pencil.hasRadius()) {
            builder.setGraphicAttribute("radius", pencil.getDistance(), double.class);
        } else {
            builder.appendPoint(new Point(
                            pencil.getEndPoint().getX(),
                            pencil.getEndPoint().getY()
                    )
            );
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
