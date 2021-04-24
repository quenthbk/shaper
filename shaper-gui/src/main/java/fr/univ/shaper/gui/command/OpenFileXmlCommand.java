package fr.univ.shaper.gui.command;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.xml.DirectorXML;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.util.Contract;

import java.io.File;
import java.io.IOException;

public class OpenFileXmlCommand implements Command {

    private final File file;

    private final GraphicBuilder builder;

    public OpenFileXmlCommand(File file, GraphicBuilder builder) {
        Contract.assertThat(file != null, "Le fichier ne doit pas être null");
        this.file = file;
        this.builder = builder;
    }

    @Override
    public void runCommand(DrawingBoard controller) {
        Director director = new DirectorXML();

        try {
            Layer layer = director.load(file, builder);
            if (layer != null) {
                controller.setLayerRoot(layer);
                controller.setDirector(director);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
