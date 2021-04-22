package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.util.Contract;

public class DrawingBoardImpl extends AbstractListenable implements DrawingBoard {

    private Layer layerRoot;

    private GraphicElement selectedElement;

    private final Pencil pencil;

    private Director director;

    public DrawingBoardImpl(Pencil pencil) {
        Contract.assertThat(pencil != null, "le crayon ne doit pas être null");
        layerRoot = new Layer();
        this.pencil = pencil;
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
    public void setLayerRoot(Layer root) {
        Contract.assertThat(root != null, "Le Layer situé à la racine ne peut pas être null");
        Layer old = layerRoot;
        layerRoot = root;
        firePropertyChange("layerRoot", old, layerRoot);
    }

    @Override
    public Director getDirector() {
        return director;
    }

    @Override
    public void setDirector(Director director) {
        Director old = this.director;
        this.director = director;
        firePropertyChange("director", old, director);
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
    public void run(Command command) {
        Contract.assertThat(command != null, "Le commande ne doit pas être null");
        command.runCommand(this);
    }

    @Override
    public boolean isNew() {
        return director == null || ! director.fileIsPresent();
    }

    @Override
    public boolean unsaved() {
        return true;
    }
}
