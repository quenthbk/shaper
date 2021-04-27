package fr.univ.shaper;


import fr.univ.shaper.surface.ShaperConverter;
import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.xml.DirectorXML;

import java.io.File;
import java.io.IOException;

public class ConverterApp {

    private static final String usage = "Usage : java -jar convert-shaper.jar fichier-source fichier-destination";

    private static final GraphicFactoryHandler handler = GraphicFactoryHandler.newInstance();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Le nombre d'arguments de correspond pas\n" + usage);
            return;
        }

        String src = args[0];
        String dest = args[1];

        try {
            new ShaperConverter(
                    new DirectorXML(),
                    handler.getDefaultFactory(),
                    new DefaultGraphicBuilder(handler))
                    .convert(new File(src), new File(dest));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
