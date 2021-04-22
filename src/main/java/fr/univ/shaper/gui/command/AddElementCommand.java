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
        GraphicElement element = controller.getSelectedElement();
        Contract.assertThat(element != null,
                "L'élément ne doit pas être null");

        controller.getLayerRoot().append(element);
        controller.setSelectedElement(null);
        if (visitor != null) {
            element.accept(visitor);
        }
    }
}
