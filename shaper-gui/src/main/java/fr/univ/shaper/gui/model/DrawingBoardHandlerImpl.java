package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.gui.command.UnperformedCommandException;
import fr.univ.shaper.util.Contract;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public class DrawingBoardHandlerImpl extends AbstractListenable implements DrawingBoardHandler {

    private DrawingBoard drawingBoard;

    private final Pencil pencil;

    private Director director;

    public DrawingBoardHandlerImpl() {
        drawingBoard = null;
        pencil = new PencilImpl();
    }

    @Override
    public void createNewDrawingBoard(Dimension dimension) {
        DrawingBoard oldBoard = this.drawingBoard;
        this.drawingBoard = new DrawingBoardImpl(dimension, pencil);
        firePropertyChange("drawingBoard", oldBoard, drawingBoard);
    }

    @Override
    public void setDirector(Director director) {
        Director old = this.director;
        this.director = director;
        firePropertyChange("director", old, director);
    }

    @Override
    public Director getDirector() {
        return director;
    }

    @Override
    public void openDrawingBoard(File file, Director director) throws IOException {
        Contract.assertThat(director != null, "Le directeur ne doit pas être null");
        Layer layer = director.load(file, new DefaultGraphicBuilder(HANDLER));
        DrawingBoard old = this.drawingBoard;
        this.drawingBoard = new DrawingBoardImpl(layer, pencil);
        firePropertyChange("drawingBoard", old, drawingBoard);
        setDirector(director);
    }

    @Override
    public void saveDrawingBoard() throws IOException {
        if (drawIsNew()) {
            throw new IOException("Le dessin ne doit être déjà enregistré ou " +
                    "ouvert pour utilisé cette commande");
        }
        director.save(drawingBoard.getLayerRoot());
    }

    @Override
    public void saveDrawingBoard(File file, Director director) throws IOException {
        if (drawingBoard == null) {
            throw new IOException("Aucun dessin n'a été créé ou ouvert");
        }
        director.saveAs(file, drawingBoard.getLayerRoot());
        setDirector(director);
    }

    @Override
    public DrawingBoard getDrawingBoard() {
        return drawingBoard;
    }

    @Override
    public void doCommand(Command command) throws UnperformedCommandException {
        Contract.assertThat(command != null, "Le commande ne doit pas être null");
        if (drawingBoard == null) {
            throw new UnperformedCommandException("La commande n'a pas pu être éxécuté car le dessin n'a pas été" +
                    " créé ni ouvert.");
        }
        drawingBoard.run(command);
    }

    @Override
    public Pencil getPencil() {
        return pencil;
    }

    public boolean drawIsNew() {
        return director == null || ! director.fileIsPresent();
    }
}
