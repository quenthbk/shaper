package fr.univ.shaper;


import fr.univ.shaper.surface.SurfaceCalculate;
import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.xml.DirectorXML;

import java.io.File;
import java.io.IOException;

public class SurfaceApp {

    private static final String usage = "Usage : java -jar shaper-surface.jar fichier-source";

    private static final GraphicFactoryHandler handler = GraphicFactoryHandler.newInstance();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Le nombre d'arguments de correspond pas\n" + usage);
            return;
        }

        String src = args[0];

        try {
            new SurfaceCalculate(
                    new DirectorXML(),
                    new DefaultGraphicBuilder(handler))
                    .calcul(new File(src));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
