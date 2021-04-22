package fr.univ.shaper.gui;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.model.DrawingBoardImpl;
//import fr.univ.shaper.gui.controller.KeyController;
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

    private final DrawingBoard controller;


    public ShaperViewer() {
        controller = new DrawingBoardImpl();

        draw = new DrawingArea(controller);
        toolPanel = new ToolPanel(controller);
        menu = new Menu(controller);

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
        controller.addLayerRootChangeListener(result -> {
            draw.clear();
            result.accept(new DrawGraphicVisitor(draw.getGraphic()));
            draw.repaint(100);
        });

        controller.addDirectorChangeListener(result -> {
            if (result != null) {
                frame.setTitle(TITLE + " - " + result.getFile().getName());
            } else {
                frame.setTitle(TITLE);
            }
        });
    }
}
