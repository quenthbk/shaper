package fr.univ.shaper;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.Layer;

import java.awt.*;

public class Client {
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
