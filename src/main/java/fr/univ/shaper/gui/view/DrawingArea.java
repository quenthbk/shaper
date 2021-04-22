package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.gui.model.DrawingBoard;
//import fr.univ.shaper.gui.controller.KeyController;
import fr.univ.shaper.gui.command.AddElementCommand;
import fr.univ.shaper.gui.command.BuildElementCommand;
import fr.univ.shaper.gui.command.StartDrawingCommand;
import fr.univ.shaper.gui.render.DrawGraphicVisitor;

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
    private final DrawingBoard drawingBoard;

    /**
     * Un visiteur permettant de dessiner un forme "DragAndDrop"
     */
    private DrawGraphicVisitor visitor;

    private int width = 800;

    private int height = 600;

    public DrawingArea(DrawingBoard drawingBoard) {
        setBackground(Color.WHITE);
        this.drawingBoard = drawingBoard;
        createEmptyImage();
        visitor = new DrawGraphicVisitor(getGraphic());

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

        GraphicElement elementDragged = drawingBoard.getSelectedElement();

        if (elementDragged != null) {
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

        private StartDrawingCommand startDrawingCommand;

        private final BuildElementCommand buildElementCommand;

        private final DrawGraphicVisitor drawGraphicVisitor;

        DrawingMouseListener() {
            buildElementCommand = new BuildElementCommand(
                    new DefaultGraphicBuilder(GraphicFactoryHandler.newInstance())
            );

            drawGraphicVisitor = new DrawGraphicVisitor(getGraphic());
        }

        public void mousePressed(MouseEvent mouseEvent) {
            Point startPoint = mouseEvent.getPoint();
            if (startDrawingCommand == null) {
                startDrawingCommand = new StartDrawingCommand(startPoint);
            } else {
                startDrawingCommand.setPoint(startPoint);
            }
            drawingBoard.run(startDrawingCommand);
        }

        public void mouseDragged(MouseEvent mouseEvent) {
            drawingBoard.getPencil().setEndPoint(mouseEvent.getPoint());
            drawingBoard.run(buildElementCommand);

            // Repeindre seulement le composant sans changer le dessin
            repaint();
        }

        public void mouseReleased(MouseEvent mouseEvent) {
            if(drawingBoard.getSelectedElement() != null) {
                drawGraphicVisitor.setGraphics(getGraphic());
                drawingBoard.run(new AddElementCommand(drawGraphicVisitor));
            }
        }
    }
}
