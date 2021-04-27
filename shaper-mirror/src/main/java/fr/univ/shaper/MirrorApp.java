package fr.univ.shaper;


import fr.univ.shaper.mirror.MirrorConverter;
import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.xml.DirectorXML;

import java.io.File;
import java.io.IOException;

public class MirrorApp {

    private static final String usage = "Usage : java -jar shaper-mirror.jar fichier-source fichier-destination";

    private static final GraphicFactoryHandler handler = GraphicFactoryHandler.newInstance();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Le nombre d'arguments de correspond pas\n" + usage);
            return;
        }

        String src = args[0];
        String dest = args[1];

        try {
            new MirrorConverter(
                    new DirectorXML(),
                    new DefaultGraphicBuilder(handler))
                    .convert(new File(src), new File(dest));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
