package fr.univ.shaper.gui.controller.command;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.file.xml.DirectorXML;
import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.util.Contract;

import java.io.File;

public class OpenFileXmlCommand implements DrawCommand {

    private final File file;

    public OpenFileXmlCommand(File file) {
        Contract.assertThat(file != null, "Le fichier ne doit pas être null");
        this.file = file;
    }

    @Override
    public void runCommand(DrawController controller) {
        Director director = controller.getDirector();
        if (! (director instanceof DirectorXML)) {
            director = new DirectorXML();
        }

        Layer layer = director.load(file, controller.getBuilder());
        if (layer != null) {
            controller.setLayerRoot(layer);
            controller.setDirector(director);
        }
    }
}
