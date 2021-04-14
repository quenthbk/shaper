package fr.univ.shaper.gui;
import fr.univ.shaper.graphic.GraphicElement;
import fr.univ.shaper.graphic.GraphicVisitor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class GraphicViewer extends JFrame{
    private final int width = 800;
    private final int height = 600;
    private final Graphics2D onscreen;


    public GraphicViewer() {
        setVisible(true);
        setSize(width, height);
        setTitle("Afficheur de dessin");

        BufferedImage onscreenImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ImageIcon icon = new ImageIcon(onscreenImage);
        JLabel draw = new JLabel(icon);

        getContentPane().add(draw);
        onscreen  = onscreenImage.createGraphics();

        // Closing any view will quit :
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public Graphics2D getOnscreen() {
        return this.onscreen;
    }

    public void draw(GraphicElement shape, GraphicVisitor visitor) {
        shape.accept(visitor);
        repaint(100);
    }
}
