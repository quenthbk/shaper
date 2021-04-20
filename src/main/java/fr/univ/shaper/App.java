package fr.univ.shaper;


import fr.univ.shaper.gui.ShaperViewer;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShaperViewer().show());
    }
}
