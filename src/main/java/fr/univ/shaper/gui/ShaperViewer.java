package fr.univ.shaper.gui;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.model.DrawingBoardImpl;
import fr.univ.shaper.gui.model.Pencil;
import fr.univ.shaper.gui.model.PencilImpl;
import fr.univ.shaper.gui.view.Menu;
import fr.univ.shaper.gui.view.ToolPanel;
import fr.univ.shaper.gui.view.DrawingArea;
import fr.univ.shaper.gui.render.DrawGraphicVisitor;

import javax.swing.*;
import java.awt.*;

/**
 *  Tips pour dessiner avec Swing:
 *
 *  https://tips4java.wordpress.com/2009/05/08/custom-painting-approaches/
 */
public class ShaperViewer {

    static public final String TITLE = "Shaper";

    private JFrame frame;

    private DrawingArea draw;

    private ToolPanel toolPanel;

    private Menu menu;

    private final DrawingBoard drawingBoard;

    private final Pencil pencil;


    public ShaperViewer() {
        pencil = new PencilImpl();
        drawingBoard = new DrawingBoardImpl(pencil);
        createView();
        createController();
        placeComponent();
    }

    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createView() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame(TITLE);
        draw = new DrawingArea(drawingBoard);
        toolPanel = new ToolPanel(pencil);
        menu = new Menu(drawingBoard);
    }

    private void placeComponent() {
        frame.setJMenuBar(menu);
        frame.getContentPane().add(draw);
        frame.getContentPane().add(toolPanel, BorderLayout.WEST);
    }

    private void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingBoard.addPropertyChangeListener("layerRoot", event -> {
            draw.clear();
            Layer layer = (Layer) event.getNewValue();
            layer.accept(new DrawGraphicVisitor(draw.getGraphic()));
            draw.repaint(100);
        });

        drawingBoard.addPropertyChangeListener("director", event -> {
            Director director = (Director) event.getNewValue();
            if (director != null) {
                frame.setTitle(TITLE + " - " + director.getFile().getName());
            } else {
                frame.setTitle(TITLE);
            }
        });
    }
}
