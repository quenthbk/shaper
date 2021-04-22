package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

public class AddElementCommand implements Command {

    private GraphicVisitor visitor;

    public AddElementCommand(GraphicVisitor visitor) {
        this.visitor = visitor;
    }

    public AddElementCommand() { }

    public void setVisitor(GraphicVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void runCommand(DrawingBoard controller) {
        Contract.assertThat(controller.getSelectedElement() != null,
                "L'élément ne doit pas être null");
        GraphicElement element = controller.getSelectedElement();
        controller.getLayerRoot().append(element);
        controller.getPencil().reset();
        controller.setSelectedElement(null);
        if (visitor != null) {
            element.accept(visitor);
        }
    }
}
