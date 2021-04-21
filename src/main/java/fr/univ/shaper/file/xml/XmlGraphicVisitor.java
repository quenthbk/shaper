package fr.univ.shaper.file.xml;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;

import java.awt.desktop.SystemEventListener;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.jar.Attributes;

public class XmlGraphicVisitor implements GraphicVisitor {

    private final OutputStreamWriter writer;

    private Stack<Layer> layers;

    public XmlGraphicVisitor(OutputStreamWriter writer, Layer rootElement) {
        this.writer = writer;
        layers = new Stack<>();
        layers.push(rootElement);
    }

    @Override
    public void visitLayer(Layer element) {
        layers.peek().append(element);
        layers.push(element);

    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {

    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {

    }

    @Override
    public void visitPerfectLine(PerfectLine element) {

    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {

    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {

    }

    @Override
    public void visitNoisyLine(NoisyLine element) {

    }

    private void lookingFor(GraphicElement element) {
        Method[] method = element.getClass().getDeclaredMethods();
        for (Method m : method) {
            if (m.getName().startsWith("get")) {
                System.out.println("get");
            }
        }
    }

    private String xmlWrapper(String elementName, Attributes attributes, boolean wrapElement) {
        //attributes.getValue("sdsd")
        return "<" + elementName + ">";
    }
}
