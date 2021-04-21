package fr.univ.shaper.gui.controller.command;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.gui.controller.DrawController;

public class NewDrawCommand implements DrawCommand {
    @Override
    public void runCommand(DrawController controller) {
        controller.setDirector(null);
        controller.setLayerRoot(new Layer());
    }
}
