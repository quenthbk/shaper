package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.FilterGraphicVisitor;
import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

import java.util.Collection;

public class FindWithFilterCommand implements Command {

    private FilterGraphicVisitor filter;

    private Collection<GraphicElement> result;

    public FindWithFilterCommand(FilterGraphicVisitor filter) {
        Contract.assertThat(filter != null, "Le filtre ne doit pas être null");
        this.filter = filter;
    }

    public void setPoint(FilterGraphicVisitor filter) {
        Contract.assertThat(filter != null, "Le filtre ne doit pas être null");
        this.filter = filter;
    }

    public Collection<GraphicElement> getResult() {
        return result;
    }

    @Override
    public void runCommand(DrawingBoard controller) {
        controller.getLayerRoot().accept(filter);
        result = filter.getResult();
    }
}
