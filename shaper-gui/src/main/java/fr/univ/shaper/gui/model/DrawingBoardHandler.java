package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.command.Command;
import fr.univ.shaper.gui.command.UnperformedCommandException;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public interface DrawingBoardHandler extends Listenable {

    GraphicFactoryHandler HANDLER = GraphicFactoryHandler.newInstance();

    void createNewDrawingBoard(Dimension dimension);

    void setDirector(Director director);

    Director getDirector();

    void openDrawingBoard(File file, Director director) throws IOException;

    void saveDrawingBoard() throws IOException;

    void saveDrawingBoard(File file, Director director) throws IOException;

    DrawingBoard getDrawingBoard();

    void doCommand(Command command) throws UnperformedCommandException;

    Pencil getPencil();

    boolean drawIsNew();
}
