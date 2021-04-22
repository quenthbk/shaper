package fr.univ.shaper.gui.model;

import java.beans.PropertyChangeListener;

public interface Listenable {

    void addPropertyChangeListener(String name, PropertyChangeListener listener);

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(String name, PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
