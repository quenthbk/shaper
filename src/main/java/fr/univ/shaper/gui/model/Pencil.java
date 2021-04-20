package fr.univ.shaper.gui.model;

import fr.univ.shaper.util.Contract;

import java.awt.*;
import java.awt.geom.Point2D;

public class Pencil {
    private Point2D startPoint;

    private Point2D endPoint;

    private String shapeName;

    private String shapeType;

    private Color color;

    public Pencil() {

    }

    public Pencil(String shapeName) {
        this.shapeName = shapeName;
    }

    public void reset() {
        startPoint = null;
        endPoint = null;
    }

    /**
     * Indique si le crayon est entrain de dessiner
     *
     * @return true si getStartPoint(), color, shapeName sont différent de null et endPoint
     *      égal à null
     */
    public boolean isDrawing() {
        return startPoint != null &&
                color != null &&
                shapeName != null;
    }

    public void putPencil(Point2D point) {
        Contract.assertThat(shapeName != null, "La forme doit être sélectionnée avant " +
                "de dessiner");
        Contract.assertThat(endPoint == null, "Pour poser le crayon vous devez d'abord " +
                "réinitialisé celui-ci");
        Contract.assertThat(color != null, "La couleur doit être sélectionné avant " +
                "de dessiner");
        startPoint = point;
    }

    public void upPencil(Point2D point) {
        Contract.assertThat(isDrawing(), "isDrawing() doit être égal à true pour relâcher " +
                "le crayon");
        endPoint = point;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setShapeName(String name) {
        this.shapeName = name;
    }

    public void setShapeType(String name) {
        this.shapeType = name;
    }

    public String getShapeName() {
        return shapeName;
    }

    public String getShapeType() {
        return shapeType;
    }

    public double getDistance() {
        return startPoint.distance(endPoint);
    }

    public boolean hasRadius() {
        return "circle".equals(shapeName);
    }

    public boolean canDraw() {
        return shapeName != null;
    }
}
