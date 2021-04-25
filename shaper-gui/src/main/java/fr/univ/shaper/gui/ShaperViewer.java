package fr.univ.shaper.gui;

import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.gui.model.*;
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

    private final DrawingBoardHandler drawingBoardHandler;

    public ShaperViewer() {
        drawingBoardHandler = new DrawingBoardHandlerImpl();
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

        menu = new Menu(drawingBoardHandler);
        toolPanel = new ToolPanel(drawingBoardHandler.getPencil());
    }

    private void placeComponent() {
        frame.setJMenuBar(menu);
        frame.getContentPane().add(toolPanel, BorderLayout.WEST);
    }

    private void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingBoardHandler.addPropertyChangeListener("drawingBoard", event -> {
            if (draw != null) {
                System.out.println("Supression du dessin en cours..");
                frame.remove(draw);
            }

            System.out.println("Création du nouveau dessin");
            draw = new DrawingArea((DrawingBoard) event.getNewValue());
            frame.add(draw);
            frame.pack();
        });

        drawingBoardHandler.addPropertyChangeListener("director", event -> {
            Director director = (Director) event.getNewValue();
            if (director != null && director.getFile() != null) {
                frame.setTitle(TITLE + " - " + director.getFile().getName());
            } else {
                frame.setTitle(TITLE);
            }
        });
    }
}
