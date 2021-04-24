package fr.univ.shaper.gui.command;

import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

public class SaveCommand implements Command {

    @Override
    public void runCommand(DrawingBoard controller) {
        Contract.assertThat(! controller.isNew(), "Aucun fichier n'est présent." +
                " Impossible de l'enregister.");
        Director director = controller.getDirector();
        director.save(controller.getLayerRoot());
    }
}
