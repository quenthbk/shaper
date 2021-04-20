package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicElement;

public interface ShaperFileLoader {
    GraphicElement read(String filename);
}
