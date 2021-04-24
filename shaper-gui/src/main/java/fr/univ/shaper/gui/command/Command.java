package fr.univ.shaper.gui.command;

import fr.univ.shaper.gui.model.DrawingBoard;

public interface Command {
    void runCommand(DrawingBoard controller);
}
