package fr.univ.shaper.gui;

import fr.univ.shaper.graphic.GraphicElement;
import fr.univ.shaper.graphic.GraphicFactory;
import fr.univ.shaper.graphic.element.Layer;

import java.awt.*;
import java.util.ArrayList;

public class Client {
    public static java.util.List<GraphicElement> getDemo(GraphicFactory factory) {
        java.util.List<GraphicElement> ls= new ArrayList<>();
        ls.add(factory.createLine(0, 500, 800, 500, Color.GREEN));
        ls.add(factory.createLine(300, 0, 0, 300, Color.YELLOW));

        ls.add(factory.createLine(30, 300, 180, 200, Color.BLUE));
        ls.add(factory.createLine(330, 300, 180, 200, Color.BLUE));
        ls.add(factory.createRectangle(30, 300,330, 500, Color.RED));

        double sunX = 600;
        double sunY = 120;
        double sunRad = 60;
        ls.add(factory.createCircle(sunX, sunY, sunRad, Color.BLACK));
        int sunRay = 20;
        for (int i=0; i<sunRay; ++i) {
            double tau=i*2*Math.PI/sunRay;
            ls.add(factory.createLine(sunX+(sunRad+5)*Math.cos(tau),
                    sunY-(sunRad+5)*Math.sin(tau),
                    sunX+(1.5*sunRad+5)*Math.cos(tau),
                    sunY-(1.5*sunRad+5)*Math.sin(tau),
                    Color.BLACK));
        }

        double manX=600;
        double manY=450;
        ls.add(factory.createLine(manX, manY-70, manX-40, manY-110, Color.RED));
        ls.add(factory.createLine(manX, manY-70, manX+40, manY-110, Color.RED));
        ls.add(factory.createCircle(manX, manY-120, 20, Color.GRAY));
        ls.add(factory.createLine(manX, manY, manX, manY-100, Color.BLUE));
        ls.add(factory.createLine(manX, manY, manX-20, manY+50, Color.BLACK));
        ls.add(factory.createLine(manX, manY, manX+20, manY+50, Color.BLACK));


        return ls;
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
