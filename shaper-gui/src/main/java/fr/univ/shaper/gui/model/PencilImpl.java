package fr.univ.shaper.gui.model;

import fr.univ.shaper.core.element.DrawingConstants;
import fr.univ.shaper.util.Contract;

import java.awt.*;
import java.awt.geom.Point2D;

public class PencilImpl extends AbstractListenable implements Pencil {

    private final Object lock = new Object();

    private Color color;

    private String graphicElementName;

    private String graphicElementType;

    private Point2D startPoint;

    private Point2D endPoint;

    public PencilImpl() {
        color = Color.BLACK;
    }

    @Override
    public boolean canDraw() {
        return startPoint != null &&
                endPoint != null &&
                graphicElementName != null;
    }

    @Override
    public boolean hasRadius() {
        return DrawingConstants.CIRCLE.equals(getGraphicElementName());
    }

    @Override
    public Color getColor() {
        synchronized (lock) {
            return color;
        }
    }

    @Override
    public Point2D getStartPoint() {
        return startPoint;
    }

    @Override
    public Point2D getEndPoint() {
        return endPoint;
    }

    @Override
    public String getGraphicElementName() {
        return graphicElementName;
    }

    @Override
    public String getGraphicElementType() {
        return graphicElementType;
    }

    @Override
    public double getDistance() {
        if (startPoint == null || endPoint == null) {
            return 0;
        }
        return startPoint.distance(endPoint);
    }

    @Override
    public void setColor(Color color) {
        Contract.assertThat(color != null, "La couleur ne doit pas être null");
        Color old = this.color;
        this.color = color;
        firePropertyChange("color", old, color);
    }

    @Override
    public void setGraphicElementName(String name) {
        Contract.assertThat(name != null, "Le nom ne doit pas être null");
        String old = this.graphicElementName;
        this.graphicElementName = name;
        firePropertyChange("graphicElementName", old, graphicElementName);
    }

    @Override
    public void setGraphicElementType(String type) {
        Contract.assertThat(type != null, "Le nom ne doit pas être null");
        String old = this.graphicElementType;
        this.graphicElementType = type;
        firePropertyChange("graphicElementType", old, graphicElementType);
    }

    @Override
    public void setStartPoint(Point2D point) {
        Point2D old = this.startPoint;
        this.startPoint = point;
        firePropertyChange("startPoint", old, startPoint);
    }

    @Override
    public void setEndPoint(Point2D point) {
        Point2D old = this.endPoint;
        this.endPoint = point;
        firePropertyChange("endPoint", old, endPoint);
    }

    @Override
    public String toString() {
        return "PencilImpl{" +
                "color=" + color +
                ", graphicElementName='" + graphicElementName + '\'' +
                ", graphicElementType='" + graphicElementType + '\'' +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
