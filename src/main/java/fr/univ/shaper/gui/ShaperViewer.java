package fr.univ.shaper.gui;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.model.DrawingBoardImpl;
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

    private final JFrame frame;

    private final DrawingArea draw;

    private final ToolPanel toolPanel;

    private final Menu menu;

    private final DrawingBoard drawingBoard;


    public ShaperViewer() {
        drawingBoard = new DrawingBoardImpl();

        draw = new DrawingArea(drawingBoard);
        toolPanel = new ToolPanel(drawingBoard);
        menu = new Menu(drawingBoard);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createController();
    }

    public void show() {
        placeComponent();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponent() {
        frame.setJMenuBar(menu);
        frame.getContentPane().add(draw);
        frame.getContentPane().add(toolPanel, BorderLayout.WEST);
    }

    private void createController() {
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
