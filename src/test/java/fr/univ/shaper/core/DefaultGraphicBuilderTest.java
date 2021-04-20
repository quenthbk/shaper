package fr.univ.shaper.core;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class DefaultGraphicBuilderTest {

    private GraphicBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new DefaultGraphicBuilder(GraphicFactoryHandler.newInstance());
    }

    @Test
    public void shouldBuildAPerfectRectangle() throws BadGraphicContextException {
        // GIVEN
        String name = "rectangle";
        Point p0 = new Point(100, 100);
        Point p1 = new Point(200, 200);
        Color color = Color.CYAN;

        builder.setGraphicName(name)
                .appendPoint(p0)
                .appendPoint(p1)
                .setGraphicAttribute("color", color, Color.class);

        // WHEN
        GraphicElement result = builder.build();

        // THEN
        PerfectRectangle expected = new PerfectRectangle(p0.getX(), p0.getY(), p1.getX(), p1.getY(), color);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBuildAPerfectCircle() throws BadGraphicContextException {
        // GIVEN
        String name = "circle";
        Point p0 = new Point(100, 100);
        double radius = 100;
        Color color = Color.CYAN;

        builder.setGraphicName(name)
                .appendPoint(p0)
                .setGraphicAttribute("radius", radius, Double.class)
                .setGraphicAttribute("color", color, Color.class);

        // WHEN
        GraphicElement result = builder.build();

        // THEN
        PerfectCircle expected = new PerfectCircle(p0.getX(), p0.getY(), radius, color);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBuildAPerfectLine() throws BadGraphicContextException {
        // GIVEN
        String name = "line";
        Point p0 = new Point(100, 100);
        Point p1 = new Point(100, 100);
        Color color = Color.CYAN;

        builder.setGraphicName(name)
                .appendPoint(p0)
                .appendPoint(p1)
                .setGraphicAttribute("color", color, Color.class);

        // WHEN
        GraphicElement result = builder.build();

        // THEN
        PerfectLine expected = new PerfectLine(p0.getX(), p0.getY(), p1.getX(), p1.getY(), color);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBuildANoisyLine() throws BadGraphicContextException {
        // GIVEN
        String name = "line";
        String type = "noisy";
        Point p0 = new Point(100, 100);
        Point p1 = new Point(200, 200);
        Color color = Color.CYAN;
        double noiseX = 1.5;
        double noiseY = 1.5;

        builder.setGraphicName(name)
                .setGraphicType(type)
                .appendPoint(p0)
                .appendPoint(p1)
                .setGraphicAttribute("color", color, Color.class)
                .setGraphicAttribute("noiseX", noiseX, double.class)
                .setGraphicAttribute("noiseY", noiseY, double.class);

        // WHEN
        GraphicElement result = builder.build();

        // THEN
        NoisyLine expected = new NoisyLine(p0.getX(), p0.getY(), p1.getX(), p1.getY(), color);
        expected.setNoiseX(noiseX);
        expected.setNoiseY(noiseY);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldBuildALayer() throws BadGraphicContextException {
        // GIVEN
        String name = "layer";
        builder.setGraphicName(name);

        // WHEN
        GraphicElement result = builder.build();

        // THEN
        Layer expected = new Layer();
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
