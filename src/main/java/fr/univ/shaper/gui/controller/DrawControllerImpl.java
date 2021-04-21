package fr.univ.shaper.gui.controller;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.Point;
import fr.univ.shaper.core.exception.BadGraphicContextException;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.controller.command.DrawCommand;
import fr.univ.shaper.gui.model.Pencil;
import fr.univ.shaper.util.Contract;
import fr.univ.shaper.core.GraphicStateListener;
import fr.univ.shaper.visitor.PrintGraphicVisitor;

import java.awt.*;
import java.awt.geom.Point2D;

public class DrawControllerImpl implements DrawController {

    private Layer layerRoot;

    private final GraphicBuilder builder;

    private GraphicStateListener graphicStateListener;

    private GraphicElement draggedElement;

    private final Pencil pencil;

    private Director director;

    private ChangeListener<Director> directorChangeListener;

    private ChangeListener<Layer> layerRootChangeListener;

    public DrawControllerImpl(GraphicBuilder builder) {
        Contract.assertThat(builder != null, "Le builder ne doit pas être null");
        this.builder = builder;
        layerRoot = new Layer();
        pencil = new Pencil();
    }

    @Override
    public void selectGraphicElementName(String name) {
        Contract.assertThat(name != null, "Le paramètre name ne doit pas être null");
        pencil.setShapeName(name);
        pickColor(Color.BLACK);
    }

    @Override
    public void selectGraphicElementType(String type) {
        Contract.assertThat(type != null, "Le paramètre type ne doit pas être null");
        Contract.assertThat(canDraw(), "Vous devez selectionner une forme avant de dessiner");
        pencil.setShapeType(type);
    }

    @Override
    public void pickColor(Color color) {
        Contract.assertThat(color != null, "Le paramètre color ne doit pas être null");
        pencil.setColor(color);
    }

    @Override
    public GraphicBuilder getBuilder() {
        return builder;
    }

    @Override
    public Layer getLayerRoot() {
        return layerRoot;
    }

    @Override
    public void setLayerRoot(Layer root) {
        Contract.assertThat(root != null, "Le Layer situé à la racine ne peut pas être null");
        layerRoot = root;
        if (layerRootChangeListener != null) {
            layerRootChangeListener.stateChanged(root);
        }
    }

    @Override
    public Director getDirector() {
        return director;
    }

    @Override
    public void setDirector(Director director) {
        this.director = director;
        if (directorChangeListener != null) {
            directorChangeListener.stateChanged(director);
        }
    }

    @Override
    public void startDrawingPosition(Point2D point) {
        Contract.assertThat(point != null, "Le paramètre point ne doit pas être null");
        System.out.println("START : "+ point);
        pencil.putPencil(point);
    }

    @Override
    public void computeDragEndDropper(Point2D point) {
        Contract.assertThat(point != null, "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.isDrawing(), "Le crayon n'est pas en train de dessiner");

        pencil.upPencil(point);
        draggedElement = buildGraphicElement();;
    }

    @Override
    public GraphicElement getDraggedElement() {
        return draggedElement;
    }

    @Override
    public void endDrawingPosition(Point2D point) {
        Contract.assertThat(point != null, "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.isDrawing(), "Le crayon n'est pas en train de dessiner");
        System.out.println("END : "+ point);

        pencil.upPencil(point);
        GraphicElement element = buildGraphicElement();


        if (element != null) {
            layerRoot.append(element);
            graphicStateListener.event(element);
            element.accept(new PrintGraphicVisitor());
        }

        draggedElement = null;
        pencil.reset();
    }

    @Override
    public boolean canDraw() {
        return pencil != null && pencil.canDraw();
    }

    @Override
    public void run(DrawCommand command) {
        Contract.assertThat(command != null, "Le commande ne doit pas être null");
        command.runCommand(this);
    }

    @Override
    public boolean fileIsPresent() {
        return director != null && director.fileIsPresent();
    }

    @Override
    public void addDrawingListener(GraphicStateListener listener) {
        Contract.assertThat(listener != null, "L'écouteur ne doit pas être égal à null");
        graphicStateListener = listener;
    }

    @Override
    public void addDirectorChangeListener(ChangeListener<Director> listener) {
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        this.directorChangeListener = listener;
    }

    @Override
    public void addLayerRootChangeListener(ChangeListener<Layer> listener) {
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        this.layerRootChangeListener = listener;
    }

    // ---------------------------------------------------------- //
    //                          TOOLS                             //
    // ---------------------------------------------------------- //

    private GraphicElement buildGraphicElement() {
        Contract.assertThat(pencil.getEndPoint() != null,
                "Le paramètre point ne doit pas être null");
        Contract.assertThat(pencil.isDrawing(), "Le crayon n'est pas en train de dessiner");

        builder.setGraphicName(pencil.getShapeName())
                .setGraphicType(pencil.getShapeType())
                .setGraphicAttribute("color", pencil.getColor(), Color.class)
                .appendPoint(new Point(
                        pencil.getStartPoint().getX(),
                        pencil.getStartPoint().getY()
                        )
                );

        if (pencil.hasRadius()) {
            builder.setGraphicAttribute("radius", pencil.getDistance(), double.class);
        } else {
            builder.appendPoint(new Point(
                    pencil.getEndPoint().getX(),
                    pencil.getEndPoint().getY()
                    )
            );
        }

        GraphicElement element = null;

        try {
            element = builder.build();
        } catch (BadGraphicContextException e) {
            e.printStackTrace();
        }

        return element;
    }
}
