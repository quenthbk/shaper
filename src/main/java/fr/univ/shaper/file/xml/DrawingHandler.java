package fr.univ.shaper.file.xml;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Stack;

import static fr.univ.shaper.core.element.DrawingConstants.*;

public class DrawingHandler extends DefaultHandler implements ContentHandler {

    public final GraphicBuilder builder;

    public Stack<String> stack;

    public Stack<Layer> layerStack;

    DrawingHandler(GraphicBuilder builder) {
        super();
        this.builder = builder;
    }

    public Layer getDrawing() {
        return layerStack.pop();
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Chargement de l'image xml en cours...");
        layerStack = new Stack<>();
        stack = new Stack<>();
        try {
            layerStack.push((Layer)
                    builder.setGraphicName(LAYER).build());
        } catch (BadGraphicContextException e) {
            throw new SAXException(e);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Chargement terminé.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        String name = qName.trim();
        System.out.println("\t".repeat(stack.size()) + name);
        stack.push(name);
        switch (name) {
            case RADIUS:
            case ROOT_ELEMENT:
                break;
            case LAYER:
                try {
                    layerStack.push((Layer) builder.setGraphicName(localName).build());
                } catch (BadGraphicContextException e) {
                    throw new SAXException(e);
                }
                break;
            case POINT:
                double x = Double.parseDouble(attrs.getValue(POINT_X));
                double y = Double.parseDouble(attrs.getValue(POINT_Y));
                builder.appendPoint(new Point(x, y));
                break;
            case NOISY:
                builder.setGraphicType(NOISY);
                builder.setGraphicAttribute(NOISE_X, Double.parseDouble(attrs.getValue(NOISE_X)), double.class);
                builder.setGraphicAttribute(NOISE_Y, Double.parseDouble(attrs.getValue(NOISE_Y)), double.class);
                break;
            default:
                builder.setGraphicName(name);
        }

        for (int i = 0; i < attrs.getLength(); i++) {
            if (attrs.getLocalName(i).equals(COLOR)) {
                String colorValue = attrs.getValue(i);
                Color color = stringToColor(colorValue);

                if (color == null) {
                    throw new SAXException("La couleur " + colorValue + " est invalide");
                }

                builder.setGraphicAttribute(COLOR,
                        color,
                        Color.class);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String name = stack.pop();
        System.out.println("\t".repeat(stack.size()) + "/" + name);
        switch (name) {
            // Dans le cas ou ce n'est pas un GraphicElement on break
            case POINT:
            case RADIUS:
            case NOISY:
            case ROOT_ELEMENT:
                break;
            case LAYER:
                Layer layer = layerStack.pop();
                // On ajoute le dernier calque au calque qui l'englobe
                layerStack.peek().append(layer);
                break;
                // Cnstructions des autres formes. (circle, rectangle ...)
            default:
                try {
                    layerStack.peek().append(builder.build());
                } catch (BadGraphicContextException e) {
                    throw new SAXException(e);
                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String name = stack.peek();
        if (name.equals(RADIUS)) {
            double value = Double.parseDouble(new String(ch, start, length));
            builder.setGraphicAttribute(name, value, double.class);
        }
    }

    private Color stringToColor(String colorValue) {
        // Essaie de décoder la couleur
        try {
            return Color.decode(colorValue);
        } catch (NumberFormatException ignored) { }

        Color color;
        color = Color.getColor(colorValue);

        if (color != null) {
            return color;
        }

        try {
            Field field = Color.class.getField(colorValue);
            color = (Color) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }

        return color;
    }
}
