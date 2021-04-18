package fr.univ.shaper.graphic.element.noisy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class NoisyLineTest {

    @Test
    public void equal_shouldBeTrue_when2NoisyLineAreEqual() {
        // GIVEN
        double x0 = 1;
        double x1 = 2;
        double y0 = 3;
        double y1 = 4;
        Color color = Color.CYAN;

        NoisyLine line1 = new NoisyLine(x0, y0, x1, y1, color);
        NoisyLine line2 = new NoisyLine(x0, y0, x1, y1, color);

        // WHEN
        boolean result = line1.equals(line2);

        // THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void equal_shouldBeTrue_when2NoisyLineAreEqualWithNoise() {
        // GIVEN
        double x0 = 1;
        double x1 = 2;
        double y0 = 3;
        double y1 = 4;
        double noiseX = 1.5;
        double noiseY = 1.2;
        Color color = Color.CYAN;

        NoisyLine line1 = new NoisyLine(x0, y0, x1, y1, color);
        line1.setNoiseX(noiseX);
        line1.setNoiseY(noiseY);
        NoisyLine line2 = new NoisyLine(x0, y0, x1, y1, color);
        line2.setNoiseX(noiseX);
        line2.setNoiseY(noiseY);

        // WHEN
        boolean result = line1.equals(line2);

        // THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void equal_shouldBeFalse_when2NoisyLineAreNotEqual() {
        // GIVEN
        double x0 = 1;
        double x1 = 2;
        double y0 = 3;
        double y1 = 4;
        Color color = Color.CYAN;

        NoisyLine line1 = new NoisyLine(x0, y0, x1, y1, color);
        NoisyLine line2 = new NoisyLine(5, y0, x1, y1, color);

        // WHEN
        boolean result = line1.equals(line2);

        // THEN
        Assertions.assertThat(result).isFalse();
    }
}
