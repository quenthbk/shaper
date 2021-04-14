package fr.univ.shaper;

import fr.univ.shaper.graphic.*;
import fr.univ.shaper.gui.Client;
import fr.univ.shaper.gui.GraphicViewer;
import fr.univ.shaper.visitor.DefaultGraphicVisitor;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class AppTest {

    public static void main() {
        GraphicViewer gv = new GraphicViewer();

        // On crée la factory
        GraphicFactory factory = new DefaultGraphicFactory();
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
        GraphicElement demo2 = getDemoGroups(factory);

        gv.draw(demo2, visitor);
    }

    public static GraphicElement getDemoGroups(GraphicFactory factory) {
        Layer result = factory.createLayer();
        Layer person =factory.createLayer();
        Layer rest = factory.createLayer();


        rest.append(factory.createLine(0, 500, 800, 500, Color.GREEN));
        rest.append(factory.createLine(300, 0, 0, 300, Color.YELLOW));

        rest.append(factory.createLine(30, 300, 180, 200, Color.BLUE));
        rest.append(factory.createLine(330, 300, 180, 200, Color.BLUE));
        rest.append(factory.createRectangle(30, 300,330, 500, Color.RED));

        double sunX = 600;
        double sunY = 120;
        double sunRad = 60;
        rest.append(factory.createCircle(sunX, sunY, sunRad, Color.BLACK));
        int sunRay = 20;
        for (int i=0; i<sunRay; ++i) {
            double tau=i*2*Math.PI/sunRay;
            rest.append(factory.createLine(sunX+(sunRad+5)*Math.cos(tau),
                    sunY-(sunRad+5)*Math.sin(tau),
                    sunX+(1.5*sunRad+5)*Math.cos(tau),
                    sunY-(1.5*sunRad+5)*Math.sin(tau),
                    Color.BLACK));
        }

        double manX=600;
        double manY=450;
        person.append(factory.createLine(manX, manY-70, manX-40, manY-110, Color.RED));
        person.append(factory.createLine(manX, manY-70, manX+40, manY-110, Color.RED));
        person.append(factory.createCircle(manX, manY-120, 20, Color.GRAY));
        person.append(factory.createLine(manX, manY, manX, manY-100, Color.BLUE));
        person.append(factory.createLine(manX, manY, manX-20, manY+50, Color.BLACK));
        person.append(factory.createLine(manX, manY, manX+20, manY+50, Color.BLACK));

        //person.move(-400, 0);

        result.append(rest);
        result.append(person);

        return result;
    }

}
