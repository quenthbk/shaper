package fr.univ.shaper.core;

import fr.univ.shaper.core.element.GraphicFactory;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import fr.univ.shaper.core.exception.GraphicTypeNotFoundException;
import fr.univ.shaper.util.Contract;
import fr.univ.shaper.util.Pair;
import fr.univ.shaper.util.StringExt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DefaultGraphicBuilder implements GraphicBuilder {

    private String name;
    private String type;
    private final Map<String, Pair<Class<?>, Object>> attributes;
    private final List<Point> points;
    private final GraphicFactoryHandler factoryHandler;

    public DefaultGraphicBuilder(GraphicFactoryHandler handler) {
        attributes = new Hashtable<>();
        points = new ArrayList<>(2);
        this.factoryHandler = handler;
    }

    @Override
    public GraphicBuilder reset() {
        name = null;
        type = null;
        attributes.clear();
        points.clear();
        return null;
    }

    @Override
    public GraphicBuilder setGraphicName(String name) {
        Contract.assertThat(name != null, "Le paramètre name ne doit pas être null");
        reset();
        this.name = StringExt.capitalize(name);
        return this;
    }

    @Override
    public GraphicBuilder setGraphicType(String type) {
        if (type == null) {
            this.type = factoryHandler.getDefaultType();
        } else {
            this.type = StringExt.capitalize(type);
        }

        return this;
    }

    @Override
    public <T> GraphicBuilder setGraphicAttribute(String key, T value, Class<T> clazz) {
        Contract.assertThat(key != null, "Le paramètre key ne doit pas être null");
        Contract.assertThat(value != null, "Le paramètre value ne doit pas être null");
        Contract.assertThat(clazz != null, "Le paramètre clazz ne doit pas être null");
        attributes.put(key, new Pair<>(clazz, value));
        return this;
    }

    @Override
    public GraphicBuilder appendPoint(Point point) {
        Contract.assertThat(point != null, "Le point ne doit pas être null");
        points.add(point);
        return this;
    }

    @Override
    public GraphicElement build() throws BadGraphicContextException {
        Contract.assertThat(name != null, "Le nom de l'élément à construire n'a pas été spécifié");

        // Récupération de la factory par rapport au type Graphic
        GraphicFactory factory;
        if (type == null) {
            factory = factoryHandler.getDefaultFactory();
            type = factoryHandler.getDefaultType();
        } else {
            try {
                factory = factoryHandler.getFactoryOf(this.type);
            } catch (GraphicTypeNotFoundException e) {
                factory = factoryHandler.getDefaultFactory();
                type = factoryHandler.getDefaultType();
            }
        }

        // Récupération de la méthode pour créer un élément graphique depuis son nom
        Optional<Method> optionalMethod = Arrays.stream(factory.getClass().getDeclaredMethods())
                .filter(method -> method.getName().contains(name))
                .findFirst();

        if (optionalMethod.isEmpty()) {
            throw new BadGraphicContextException("Le nom de l'élément graphique '" + name +
                    "' est inconnu");
        }

        Method method = optionalMethod.get();
        Object thing;

        // Récupération de l'objet en question avec la méthode retrouvée
        Object[] arguments = getListOfArguments();
        try {
            thing = method.invoke(factory, arguments);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new BadGraphicContextException("Impossible de créer un élément graphique '" + name
                    + "' de type '" + type + ". InvocationTargetException -> " + e.getMessage()
            );
        } catch (IllegalArgumentException e) {
            throw new BadGraphicContextException("Les arguments sont invalides pour l'élément '" + name +
                    "' de type '" + type + "'\n" +
                    "Nombre de paramètres attendus : " + method.getParameterCount() + '\n' +
                    "Nombre de paramètres donnés : " + arguments.length + '\n' +
                    "Liste des paramètres donnés :" + Arrays.toString(arguments)
                    , e);
        }

        // On devrait toujours avoir une instance de GraphicElement, seulement cette condition
        //  est nécessaire pour cast l'objet en graphic element
        if (!(thing instanceof GraphicElement)) {
            throw new BadGraphicContextException("L'objet attendu "+ thing.getClass().getName() +
                    " n'est de type GraphicElement\n");
        }

        GraphicElement graphicElement = (GraphicElement) thing;

        Set<String> keys = attributes.keySet();
        for (String key : keys) {
            Pair<Class<?>, Object> pair = attributes.get(key);
            String setterName = "set" + StringExt.capitalize(key);
            try {
                Method m = graphicElement.getClass()
                        .getDeclaredMethod(setterName, pair.getKey());
                m.invoke(graphicElement, pair.getValue());
            } catch (NoSuchMethodException e) {
                throw new BadGraphicContextException("Le setter " + setterName +
                        " de l'attribut '"+ key +
                        "' dont le type est " + pair.getKey().getName() +
                        " n'existe pas pour cet élément graphique\n" +
                        getCheckup(graphicElement));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        reset();
        return graphicElement;
    }

    private Object[] getListOfArguments() {
        List<Object> list = new ArrayList<>(5);

        // Récupére les informations d'un point et les supprimes de la liste
        List<Point> pts = new ArrayList<>(points);
        for (Point p : pts) {
            list.add(p.getX());
            list.add(p.getY());
            points.remove(p);
        }

        // Récupére l'attribut radius et le supprime de la liste des attributs
        Pair<Class<?>, Object> object = attributes.get("radius");
        if (object != null) {
            list.add(object.getValue());
            attributes.remove("radius");
        }

        // Récupére l'attribut color et le supprime de la liste des attributs
        object = attributes.get("color");
        if (object != null) {
            list.add(object.getValue());
            attributes.remove("color");
        }

        return list.toArray();
    }

    private String getCheckup(GraphicElement e) {
        StringBuilder str = new StringBuilder();
        str.append("-----------------------\n");
        if (e != null) {
            str.append("Nom de la classe : ")
                    .append(e.getClass().getName())
                    .append('\n');
        }

        str.append("Type de l'élément : ")
                .append(type)
                .append('\n')
                .append("Nom de l'élément: ")
                .append(name)
                .append('\n')
                .append("---- parametres ----\n");

        for (Point p : points) {
            str.append(p.toString());
            str.append('\n');
        }

        for (String s : attributes.keySet()) {
            str.append(s).append(" : ").append(attributes.get(s).getValue().toString()).append('\n');
        }

        return str.toString();
    }
}
