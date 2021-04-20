package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.visitor.DefaultGraphicVisitor;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class DrawingArea extends JPanel {

    /**
     * L'image permettant d'afficher le contenu dessiné
     */
    private BufferedImage image;

    /**
     * Le contrôleur de dessin
     */
    private final DrawController controller;

    /**
     * Un visiteur permettant de dessiner un forme "DragAndDrop"
     */
    private DefaultGraphicVisitor visitor;

    private int width = 800;

    private int height = 600;

    public DrawingArea(DrawController controller) {
        setBackground(Color.WHITE);
        this.controller = controller;
        createEmptyImage();
        visitor = new DefaultGraphicVisitor(getGraphic());

        MouseInputAdapter listener = new DrawingMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ?
                super.getPreferredSize() : new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }

        GraphicElement elementDragged = controller.getDraggedElement();

        if (elementDragged != null) {
            // Créer une méthode setGraphic pour le visiteur ?
            visitor.setGraphics((Graphics2D) g);
            elementDragged.accept(visitor);
        }
    }

    public Graphics2D getGraphic() {
        return image.createGraphics();
    }

    public void clear() {
        createEmptyImage();
        repaint();
    }

    private void createEmptyImage() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        getGraphic().setColor(Color.BLACK);
    }

    /**
     * Définission des commandes avec la souris
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
