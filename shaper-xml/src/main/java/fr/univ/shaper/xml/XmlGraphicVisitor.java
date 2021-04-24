package fr.univ.shaper.xml;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;
import fr.univ.shaper.util.Pair;

import java.io.Serializable;
import java.util.*;

import static fr.univ.shaper.core.element.DrawingConstants.*;

public class XmlGraphicVisitor implements GraphicVisitor {

    /**
     * Constantes des balises XML
     */
    private final static char OPEN_TAG = '<';
    private final static char CLOSE_TAG = '>';
    private final static char ELEMENT_END_TAG = '/';
    private final static char ATTRIBUTE_ASSIGNMENT_TAG = '=';
    private final static char ATTRIBUTE_VALUE_WRAPPER = '"';
    private final static char WHITE_SPACE = ' ';
    private final static int INCREMENTAL_STEPS = 2;

    private final StringBuilder builder;

    /**
     * Stack permettant l'incrémentation
     *  Si le boolean en haut de la pile vaut true, alors l'incrémentation est
     *  necessaire
     */
    private final Stack<Boolean> stack;

    public XmlGraphicVisitor(StringBuilder stringBuilder) {
        this.builder = stringBuilder;
        stack = new Stack<>();
    }

    @Override
    public void visitLayer(Layer element) {
        String name = LAYER;

        // Start Element
        Attributes a = new Attributes();
        a.add(HEIGHT, element.getHeight());
        a.add(WIDTH, element.getWidth());
        xmlStartElement(name, a, null, true);

        // Recursion en profondeur
        element.getChildren().forEach(e -> e.accept(this));

        // End Element
        xmlEndElement(name);
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        String name = RECTANGLE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // - POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getX0());
            attrs.add(POINT_Y, element.getY0());
            xmlStartElement(POINT,attrs,null, false);

            // - POINT 2
            attrs = new Attributes();
            attrs.add(POINT_X, element.getX1());
            attrs.add(POINT_Y, element.getY1());
            xmlStartElement(POINT,attrs,null, false);
            xmlEndElement(name);
        }
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        String name = CIRCLE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getCx());
            attrs.add(POINT_Y, element.getCy());
            xmlStartElement(POINT,attrs,null, false);

            // RADIUS
            attrs = new Attributes();
            xmlStartElement(RADIUS,null, element.getRadius(), true); {
                xmlEndElement(RADIUS);
            }

            xmlEndElement(name);
        }
    }


    @Override
    public void visitPerfectLine(PerfectLine element) {
        String name = LINE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // - POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getX0());
            attrs.add(POINT_Y, element.getY0());
            xmlStartElement(POINT,attrs,null, false);

            // - POINT 2
            attrs = new Attributes();
            attrs.add(POINT_X, element.getX1());
            attrs.add(POINT_Y, element.getY1());
            xmlStartElement(POINT,attrs,null, false);
            xmlEndElement(name);
        }
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        String name = RECTANGLE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // - POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getX0());
            attrs.add(POINT_Y, element.getY0());
            xmlStartElement(POINT,attrs,null, false);

            // - POINT 2
            attrs = new Attributes();
            attrs.add(POINT_X, element.getX1());
            attrs.add(POINT_Y, element.getY1());
            xmlStartElement(POINT,attrs,null, false);

            // - NOISY
            attrs = new Attributes();
            attrs.add(NOISE_X, element.getNoiseX());
            attrs.add(NOISE_Y, element.getNoiseY());
            xmlStartElement(NOISY, attrs, null, false);
            xmlEndElement(name);
        }
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        String name = CIRCLE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getCx());
            attrs.add(POINT_Y, element.getCy());
            xmlStartElement(POINT,attrs,null, false);

            // RADIUS
            attrs = new Attributes();
            xmlStartElement(RADIUS,null, element.getRadius(), true); {
                xmlEndElement(RADIUS);
            }

            // - NOISY
            attrs = new Attributes();
            attrs.add(NOISE_X, element.getNoiseX());
            attrs.add(NOISE_Y, element.getNoiseY());
            xmlStartElement(NOISY, attrs, null, false);

            xmlEndElement(name);
        }
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        String name = LINE;
        // ATTRIBUTES
        Attributes attributes = new Attributes();
        attributes.add(COLOR, element.getColor().getRGB());

        xmlStartElement(name, attributes, null, true); {
            // - POINT 1
            Attributes attrs = new Attributes();
            attrs.add(POINT_X, element.getX0());
            attrs.add(POINT_Y, element.getY0());
            xmlStartElement(POINT,attrs,null, false);

            // - POINT 2
            attrs = new Attributes();
            attrs.add(POINT_X, element.getX1());
            attrs.add(POINT_Y, element.getY1());
            xmlStartElement(POINT,attrs,null, false);

            // - NOISY
            attrs = new Attributes();
            attrs.add(NOISE_X, element.getNoiseX());
            attrs.add(NOISE_Y, element.getNoiseY());
            xmlStartElement(NOISY, attrs, null, false);
            xmlEndElement(name);
        }
    }

    private void xmlEndElement(String elementName) {
        assert elementName != null;

        if (stack.pop()) {
            builder.append(String.valueOf(WHITE_SPACE)
                    .repeat(stack.size() * INCREMENTAL_STEPS));
        }

        builder.append(OPEN_TAG)
                .append(ELEMENT_END_TAG)
                .append(elementName)
                .append(CLOSE_TAG)
                .append('\n');
    }

    private void xmlStartElement(String elementName, Attributes attributes,
                                 Serializable content, boolean wrap) {
        assert ! (! wrap && content != null);

        builder.append(String.valueOf(WHITE_SPACE)
                .repeat(stack.size() * INCREMENTAL_STEPS));

        stack.push(content == null);

        builder.append(OPEN_TAG)
                .append(elementName);

        if (attributes != null) {
            builder.append(WHITE_SPACE);
            attributes.forEach(pair -> {
                builder.append(pair.getKey())
                        .append(ATTRIBUTE_ASSIGNMENT_TAG)
                        .append(ATTRIBUTE_VALUE_WRAPPER)
                        .append(pair.getValue())
                        .append(ATTRIBUTE_VALUE_WRAPPER)
                        .append(WHITE_SPACE);
            });
        }

        if (! wrap) {
            builder.append(ELEMENT_END_TAG);
            stack.pop();
        }

        builder.append(CLOSE_TAG);

        if (content != null) {
            builder.append(content);
        } else {
            builder.append('\n');
        }
    }

    private static class Attributes implements Iterable<Pair<String, Serializable>> {
        private final Set<Pair<String, Serializable>> attributes;

        public Attributes() {
            attributes = new HashSet<>();
        }

        @Override
        public Iterator<Pair<String, Serializable>> iterator() {
            return attributes.iterator();
        }

        public void add(String key, Serializable value) {
            attributes.add(new Pair<>(key, value));
        }
    }
}
