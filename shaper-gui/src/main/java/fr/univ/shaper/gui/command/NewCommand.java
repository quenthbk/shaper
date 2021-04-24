package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.gui.model.DrawingBoard;

public class NewCommand implements Command {
    @Override
    public void runCommand(DrawingBoard controller) {
        controller.setDirector(null);
        controller.setLayerRoot(new Layer());
    }
}
