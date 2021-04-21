package fr.univ.shaper.gui.model;

import fr.univ.shaper.util.Pair;

public class TreeObject<T> extends Pair<String, T> {
    public TreeObject(String key, T value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return getKey();
    }
}
