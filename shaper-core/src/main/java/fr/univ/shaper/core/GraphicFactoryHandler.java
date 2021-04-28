package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.noisy.NoisyGraphicFactory;
import fr.univ.shaper.core.element.perfect.PerfectGraphicFactory;
import fr.univ.shaper.core.exception.GraphicTypeNotFoundException;
import fr.univ.shaper.util.Contract;
import java.util.Hashtable;
import java.util.Map;

/**
 * Le gestionnaire des factories disponibles pour la création de sous type d'éléments graphiques.
 */
public class GraphicFactoryHandler {

    /**
     * L'instance unique de cette classe
     */
    static private GraphicFactoryHandler instance;

    /**
     * Le suffix des classes graphique factory
     */
    static public final String FACTORY_SUFFIX = "GraphicFactory";

    /**
     * Obtention d'une instance de GraphicFactoryHandler
     *
     * @return une instance de GraphicFactoryHandler
     */
    static public GraphicFactoryHandler newInstance() {
        if (instance == null) {
            instance = new GraphicFactoryHandler(new PerfectGraphicFactory())
                .appendFactory("Noisy", new NoisyGraphicFactory());
        }
        return instance;
    }

    /**
     * Récupération du nom du type d'une factory par rapport à son nom
     *
     * @param factory à rechercher
     * @return le nom du type de {factory}
     */
    static public String getTypeName(GraphicFactory factory) {
        String[] pack = factory.getClass().getName().replace(FACTORY_SUFFIX, "").split("\\.");
        return pack[pack.length - 1];
    }

    /**
     * La factory par défaut
     */
    private final GraphicFactory defaultFactory;

    /**
     * Le type par défaut
     */
    private final String defaultType;

    /**
     * Les factories connue du gestionnaire.
     */
    private final Map<String, GraphicFactory> factories;

    private GraphicFactoryHandler(GraphicFactory defaultFactory) {
        Contract.assertThat(defaultFactory != null, "La factory par defaut ne doit pas être null");
        this.defaultFactory = defaultFactory;
        this.defaultType = getTypeName(defaultFactory);
        this.factories = new Hashtable<>();
    }

    /**
     * Ajoute une factory au gestionaire
     *
     * @param type le nom du type de la factory
     * @param factory la factory à ajouter
     * @return l'objet lui même
     */
    public GraphicFactoryHandler appendFactory(String type, GraphicFactory factory) {
        factories.put(type, factory);
        return this;
    }

    /**
     * Renvoie la factory dont le type est {type}
     *
     * @param type le type de la factory
     * @return La factory dont le nom du type est {type}
     * @throws GraphicTypeNotFoundException si aucune factory n'a été trouvée
     */
    public GraphicFactory getFactoryOf(String type) throws GraphicTypeNotFoundException {
        Contract.assertThat(type != null, "Le paramètre type ne doit pas être null");
        GraphicFactory factory = factories.get(type);
        if (factory != null) {
            return factory;
        }
        throw new GraphicTypeNotFoundException("La factory du type " + type + " est inconnue " +
                "ou n'a pas été ajouté à la liste des usines connues.");
    }

    /**
     * @return la factory par défaut
     */
    public GraphicFactory getDefaultFactory() {
        return defaultFactory;
    }

    /**
     * @return le type par défaut
     */
    public String getDefaultType() {
        return defaultType;
    }
}
