package fr.univ.shaper.gui.util;

import fr.univ.shaper.util.Contract;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AbstractListenable implements Listenable {

    private PropertyChangeSupport support;

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        Contract.assertThat(name != null, "Le nom ne doit pas être null");
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        Contract.assertThat(name != null, "Le nom ne doit pas être null");
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        Contract.assertThat(listener != null, "Le listener ne doit pas être null");
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String name, Object old, Object recent) {
        if (support != null) {
            support.firePropertyChange(name, old, recent);
        }
    }

    protected void firePropertyChange(String name, int old, int recent) {
        if (support != null) {
            support.firePropertyChange(name, old, recent);
        }
    }

    protected void firePropertyChange(String name, double old, double recent) {
        if (support != null) {
            support.firePropertyChange(name, old, recent);
        }
    }

    protected void firePropertyChange(String name, long old, long recent) {
        if (support != null) {
            support.firePropertyChange(name, old, recent);
        }
    }
}
