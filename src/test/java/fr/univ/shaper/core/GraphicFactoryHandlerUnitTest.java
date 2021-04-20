package fr.univ.shaper.core;

import fr.univ.shaper.core.element.noisy.NoisyGraphicFactory;
import fr.univ.shaper.core.exception.GraphicTypeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphicFactoryHandlerUnitTest {

    private GraphicFactoryHandler handler = GraphicFactoryHandler.newInstance();

    @Test
    public void shouldInstantiateUniqueFactoryHandler() {
        // GIVEN
        // WHEN
        GraphicFactoryHandler handler = GraphicFactoryHandler.newInstance();

        // THEN
        Assertions.assertThat(handler).isSameAs(GraphicFactoryHandler.newInstance());
    }

    @Test
    public void shouldGetAnInstanceOfNoisyFactory() throws GraphicTypeNotFoundException {
        // GIVEN
        String type = "noisy";

        // WHEN
        GraphicFactory factory = handler.getFactoryOf(type);

        // THEN
        Assertions.assertThat(factory).isInstanceOf(NoisyGraphicFactory.class);
    }

    @Test
    public void shouldGetTheSameInstanceOfNoisyFactory() throws GraphicTypeNotFoundException {
        // GIVEN
        String type = "noisy";

        // WHEN
        GraphicFactory factory = handler.getFactoryOf(type);

        // THEN
        GraphicFactory expected = handler.getFactoryOf(type);
        Assertions.assertThat(factory).isSameAs(expected);
    }

    @Test
    public void shouldGetTheSameDefaultFactory() throws GraphicTypeNotFoundException {
        // GIVEN
        // WHEN
        GraphicFactory factory = handler.getDefaultFactory();

        // THEN
        GraphicFactory expected = handler.getDefaultFactory();
        Assertions.assertThat(factory).isSameAs(expected);
    }
}
