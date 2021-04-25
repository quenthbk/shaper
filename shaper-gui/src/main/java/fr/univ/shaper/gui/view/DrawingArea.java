package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.element.GraphicElement;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.gui.model.DrawingBoard;
//import fr.univ.shaper.gui.controller.KeyController;
import fr.univ.shaper.gui.command.AddElementCommand;
import fr.univ.shaper.gui.command.BuildElementCommand;
import fr.univ.shaper.gui.command.StartDrawingCommand;
import fr.univ.shaper.gui.render.DrawGraphicVisitor;
import fr.univ.shaper.util.Contract;

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
     * Un visiteur permettant de dessiner les formes "DragAndDrop"
     */
    private final DrawGraphicVisitor visitor;

    public DrawingArea(DrawingBoard drawingBoard) {
        Contract.assertThat(drawingBoard != null, "La planche à dessin ne " +
                "doit pas être null");
        setBackground(Color.WHITE);
        this.drawingBoard = drawingBoard;
        createEmptyImage();

        visitor = new DrawGraphicVisitor(getGraphic());
        drawingBoard.getLayerRoot().accept(visitor);
        repaint();

        MouseInputAdapter listener = new DrawingMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        createController();
    }

    private void createController() {
        drawingBoard.addPropertyChangeListener("layerRoot", event -> {
            clear();
            Layer layer = (Layer) event.getNewValue();
            layer.accept(new DrawGraphicVisitor(getGraphic()));
            repaint(100);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ?
                super.getPreferredSize() : drawingBoard.getDimension();
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
        image = new BufferedImage(
                drawingBoard.getDimension().width,
                drawingBoard.getDimension().height,
                BufferedImage.TYPE_INT_ARGB);
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
            try {
                drawingBoard.run(startDrawingCommand);
            } catch (fr.univ.shaper.gui.command.UnperformedCommandException e) {
                e.printStackTrace();
            }
        }

        public void mouseDragged(MouseEvent mouseEvent) {
            drawingBoard.getPencil().setEndPoint(mouseEvent.getPoint());
            try {
                drawingBoard.run(buildElementCommand);
            } catch (fr.univ.shaper.gui.command.UnperformedCommandException e) {
                e.printStackTrace();
            }

            // Repeindre seulement le composant sans changer le dessin
            repaint();
        }

        public void mouseReleased(MouseEvent mouseEvent) {
            if(drawingBoard.getSelectedElement() != null) {
                drawGraphicVisitor.setGraphics(getGraphic());
                try {
                    drawingBoard.run(new AddElementCommand(drawGraphicVisitor));
                } catch (fr.univ.shaper.gui.command.UnperformedCommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
