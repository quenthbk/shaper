package fr.univ.shaper;

import fr.univ.shaper.graphic.*;
import fr.univ.shaper.gui.Client;
import fr.univ.shaper.gui.GraphicViewer;
import fr.univ.shaper.visitor.DefaultGraphicVisitor;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args)  {
        GraphicViewer gv = new GraphicViewer();

        // On crée la factory
        GraphicFactory factory = new NoisyGraphicFactory();
//		AbstractFactory factory = new HandFactory();

        // On crée le visiteur
        GraphicVisitor visitor = new DefaultGraphicVisitor(gv.getOnscreen());

        // On crée le builder
        //GraphicFactory builder = new ConcreteDrawableBuilder(factory);

        // On crée le directeur
        //DirectorXML director = new DirectorXML(builder, "pencil.xml");



        //director.construct();
//		java.util.List<Drawable> demo=getDemo(factory);
        //java.util.List<GraphicElement> demo = director.getProduct();
        GraphicElement demo2 = Client.getDemoGroups(factory);



        gv.draw(demo2, visitor);
    }
}
