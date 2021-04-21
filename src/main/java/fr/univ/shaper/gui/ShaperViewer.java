package fr.univ.shaper.gui;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.gui.controller.DrawControllerImpl;
//import fr.univ.shaper.gui.controller.KeyController;
import fr.univ.shaper.gui.view.LayerPanel;
import fr.univ.shaper.gui.view.Menu;
import fr.univ.shaper.gui.view.ToolPanel;
import fr.univ.shaper.gui.view.DrawingArea;
import fr.univ.shaper.gui.render.DrawGraphicVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    private final DrawController controller;

    private final LayerPanel layer;



    public ShaperViewer() {
        controller = new DrawControllerImpl(
                new DefaultGraphicBuilder(GraphicFactoryHandler.newInstance())
        );

        draw = new DrawingArea(controller);
        toolPanel = new ToolPanel(controller);
        menu = new Menu(controller);
        layer = new LayerPanel(controller);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configureController();
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
        frame.getContentPane().add(layer, BorderLayout.EAST);
    }

    private void configureController() {
        controller.addDrawingListener(result -> {
            result.accept(new DrawGraphicVisitor(draw.getGraphic()));
            draw.repaint(100);
        });
    }
}
