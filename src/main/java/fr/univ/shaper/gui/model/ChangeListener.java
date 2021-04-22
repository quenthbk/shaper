package fr.univ.shaper.gui.model;

import java.util.EventListener;

public interface ChangeListener<T> extends EventListener {
    void stateChanged(T object);
}
