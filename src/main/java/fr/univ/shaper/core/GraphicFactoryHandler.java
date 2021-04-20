package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.noisy.NoisyGraphicFactory;
import fr.univ.shaper.core.element.perfect.PerfectGraphicFactory;
import fr.univ.shaper.core.exception.GraphicTypeNotFoundException;
import fr.univ.shaper.util.Contract;
import java.util.Hashtable;
import java.util.Map;

public class GraphicFactoryHandler {

    static public GraphicFactoryHandler instance;

    static public final  String FACTORY_SUFFIX = "GraphicFactory";

    static public GraphicFactoryHandler newInstance() {
        if (instance == null) {
            instance = new GraphicFactoryHandler(new PerfectGraphicFactory())
                .appendFactory("Noisy", new NoisyGraphicFactory());
        }
        return instance;
    }

    static public String getTypeName(GraphicFactory factory) {
        String[] pack = factory.getClass().getName().replace(FACTORY_SUFFIX, "").split("\\.");
        return pack[pack.length - 1];
    }

    private final GraphicFactory defaultFactory;

    private final String defaultType;

    private final Map<String, GraphicFactory> factories;

    private GraphicFactoryHandler(GraphicFactory defaultFactory) {
        Contract.assertThat(defaultFactory != null, "La factory par defaut ne doit pas être null");
        this.defaultFactory = defaultFactory;
        this.defaultType = getTypeName(defaultFactory);
        this.factories = new Hashtable<>();
    }

    public GraphicFactoryHandler appendFactory(String type, GraphicFactory factory) {
        factories.put(type, factory);
        return this;
    }

    public GraphicFactory getFactoryOf(String type) throws GraphicTypeNotFoundException {
        Contract.assertThat(type != null, "Le paramètre type ne doit pas être null");
        GraphicFactory factory = factories.get(type);
        if (factory != null) {
            return factory;
        }
        throw new GraphicTypeNotFoundException("La factory du type " + type + " est inconnue " +
                "ou n'a pas été ajouté à la liste des usines connues.");
    }

    public GraphicFactory getDefaultFactory() {
        return defaultFactory;
    }

    public String getDefaultType() {
        return defaultType;
    }
}
