package fr.univ.shaper.gui.controller.command;

import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.util.Contract;

public class SaveDrawCommand implements DrawCommand {

    @Override
    public void runCommand(DrawController controller) {
        Contract.assertThat(controller.fileIsPresent(), "Aucun fichier n'est présent." +
                " Impossible de l'enregister.");
        Director director = controller.getDirector();
        director.save(controller.getLayerRoot());
    }
}
