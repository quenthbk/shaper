package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.gui.command.UnperformedCommandException;
import fr.univ.shaper.util.Contract;

import java.awt.*;

public class DrawingBoardImpl extends AbstractListenable implements DrawingBoard {

    private final Layer layerRoot;

    private GraphicElement selectedElement;

    private final Pencil pencil;

    private final Dimension dimension;

    public DrawingBoardImpl(Dimension dimension, Pencil pencil) {
        Contract.assertThat(pencil != null, "le crayon ne doit pas être null");
        Contract.assertThat(dimension != null, "La dimension ne doit pas être null");
        layerRoot = new Layer();
        layerRoot.setWidth((int) dimension.getWidth());
        layerRoot.setHeight((int) dimension.getHeight());
        this.dimension = dimension;
        this.pencil = pencil;
    }

    public DrawingBoardImpl(Layer layer, Pencil pencil) {
        Contract.assertThat(pencil != null, "le crayon ne doit pas être null");
        Contract.assertThat(layer != null, "Le calque ne doit pas être null");
        layerRoot = layer;
        dimension = new Dimension(layer.getWidth(), layer.getHeight());
        this.pencil = pencil;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Pencil getPencil() {
        return pencil;
    }

    @Override
    public Layer getLayerRoot() {
        return layerRoot;
    }

    @Override
    public void setSelectedElement(GraphicElement element) {
        GraphicElement old = this.selectedElement;
        this.selectedElement = element;
        firePropertyChange("selectedElement", old, element);
    }

    @Override
    public GraphicElement getSelectedElement() {
        return selectedElement;
    }

    @Override
    public boolean canDraw() {
        return pencil != null && pencil.canDraw();
    }

    @Override
    public void run(Command command) throws UnperformedCommandException {
        Contract.assertThat(command != null, "Le commande ne doit pas être null");
        command.runCommand(this);
    }
}
