package fr.univ.shaper.gui.controller;

import java.util.EventListener;

public interface ChangeListener<T> extends EventListener {
    void stateChanged(T object);
}
