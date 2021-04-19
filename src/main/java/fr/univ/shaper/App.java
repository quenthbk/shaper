package fr.univ.shaper;

import fr.univ.shaper.graphic.*;
import fr.univ.shaper.graphic.element.Layer;
import fr.univ.shaper.graphic.element.noisy.NoisyGraphicFactory;
import fr.univ.shaper.gui.Client;
import fr.univ.shaper.gui.GraphicViewer;
import fr.univ.shaper.visitor.DefaultGraphicVisitor;
import fr.univ.shaper.xml.DirectorXML;
import fr.univ.shaper.xml.DrawingListener;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        GraphicViewer gv = new GraphicViewer();

        // On crée la factory
        GraphicFactory factory = new NoisyGraphicFactory();
//		AbstractFactory factory = new HandFactory();

        // On crée le visiteur
        GraphicVisitor visitor = new DefaultGraphicVisitor(gv.getOnscreen());

        // On crée le builder
        //GraphicFactory builder = new ConcreteDrawableBuilder(factory);

        // On crée le directeur
        DirectorXML director = new DirectorXML(
                new DefaultGraphicBuilder(GraphicFactoryHandler.newInstance()),
                "drawing.xml");
        director.construct(new DrawingListener() {
            @Override
            public void event(Layer result) {
                gv.draw(result, new DefaultGraphicVisitor(gv.getOnscreen()));
            }
        });
    }
}
