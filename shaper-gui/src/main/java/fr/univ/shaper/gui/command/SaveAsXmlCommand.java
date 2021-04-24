package fr.univ.shaper.gui.command;

import fr.univ.shaper.file.Director;
import fr.univ.shaper.xml.DirectorXML;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

import java.io.File;
import java.io.IOException;

public class SaveAsXmlCommand implements Command {

    private final File file;

    public SaveAsXmlCommand(File file) {
        Contract.assertThat(file != null, "Le fichier ne doit pas être null");
        this.file = file;
    }

    @Override
    public void runCommand(DrawingBoard controller) {
        Director director = controller.getDirector();
        if (!(director instanceof DirectorXML)) {
            director = new DirectorXML();
        }
        try {
            director.saveAs(file, controller.getLayerRoot());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        controller.setDirector(director);
    }
}
