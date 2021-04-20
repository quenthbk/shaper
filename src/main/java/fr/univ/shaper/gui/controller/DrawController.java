package fr.univ.shaper.gui.controller;

import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.file.FileType;
import fr.univ.shaper.xml.DrawingListener;

import java.awt.*;
import java.awt.geom.Point2D;

public interface DrawController {

    // --------------------------------------------------- //
    //                  Avant le dessin                    //
    // --------------------------------------------------- //
    void selectGraphicElementName(String name);

    void selectGraphicElementType(String type);

    void pickColor(Color color);

    // --------------------------------------------------- //
    //                Pendant le dessin                    //
    // --------------------------------------------------- //
    void startDrawingPosition(Point2D point);

    void computeDragEndDropper(Point2D point);

    /**
     * Renvoie l'élément graphique qui est en train d'être dessiné
     *
     * @return l'élément graphique qui est en train d'être dessiné
     *      null si rien n'est en cours de dessin.
     */
    GraphicElement getDraggedElement();

    void endDrawingPosition(Point2D point);

    // --------------------------------------------------- //
    //                Après le dessin                      //
    // --------------------------------------------------- //
    void saveDrawing(FileType format, String filename);

    void loadDrawing(FileType format, String filename);

    // ---------------------------------------------------- //
    //                       Flags                          //
    // ---------------------------------------------------- //
    boolean canDraw();

    // ---------------------------------------------------- //
    //                   Configuration                      //
    // ---------------------------------------------------- //

    void addDrawingListener(DrawingListener listener);
}
