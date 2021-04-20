package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.visitor.DefaultGraphicVisitor;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class DrawingArea extends JLabel {

    private BufferedImage image;

    private final DrawController controller;

    private DefaultGraphicVisitor visitor;

    private int width = 800;

    private int height = 600;

    public DrawingArea(DrawController controller) {
        setBackground(Color.WHITE); // Ne fonctionne pas ici ??

        this.controller = controller;

        createEmptyImage();
        configure();
    }

    public Graphics2D getGraphic() {
        return (Graphics2D) image.getGraphics();
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ?
                super.getPreferredSize() : new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // paint le layer déjà existant

        // peindre la figure en cours de dessin

        //  Custom code to support painting from the BufferedImage

        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }

        //  Paint the Rectangle as the mouse is being dragged

        GraphicElement elementDragged = controller.getDraggedElement();
        if (elementDragged != null && visitor != null) {
            elementDragged.accept(visitor);
        }
    }

    public void clear() {
        createEmptyImage();
        repaint();
    }

    private void createEmptyImage() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        getGraphic().setColor(Color.BLACK);
        visitor = new DefaultGraphicVisitor(getGraphic());
    }

    private void configure() {
        // Configuration des commandes avec la souris
        MouseInputAdapter listener = new DrawingMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    /**
     * Définission de chaque commande
     */
    private class DrawingMouseListener extends MouseInputAdapter {

        private Point startPoint;

        public void mousePressed(MouseEvent mouseEvent) {
            startPoint = mouseEvent.getPoint();
            controller.startDrawingPosition(mouseEvent.getPoint());
            //shape = new Rectangle();
        }

        public void mouseDragged(MouseEvent mouseEvent) {
            // TODO calculer de nouveau le "start point" à faire dans le contrôleur
            int x = Math.min(startPoint.x, mouseEvent.getX());
            int y = Math.min(startPoint.y, mouseEvent.getY());
            int width = Math.abs(startPoint.x - mouseEvent.getX());
            int height = Math.abs(startPoint.y - mouseEvent.getY());

            controller.computeDragEndDropper(mouseEvent.getPoint());
            repaint();
        }

        public void mouseReleased(MouseEvent mouseEvent) {
            /*
            TODO à faire dans le contrôleur
            if (shape.width != 0 || shape.height != 0) {
                addRectangle(shape, e.getComponent().getForeground());
            }
            */
            controller.endDrawingPosition(mouseEvent.getPoint());
        }
    }
}
