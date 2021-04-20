package fr.univ.shaper.gui.view;

import fr.univ.shaper.file.FileType;
import fr.univ.shaper.gui.controller.DrawController;
import javax.swing.*;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.CTRL_DOWN_MASK;

public class Menu extends JMenuBar {

    private final ActionListener afficherMenuListener;

    private final DrawController controller;

    public Menu(DrawController controller) {
        this.controller = controller;
        // Listener générique qui affiche l'action du menu utilisé
        afficherMenuListener = event ->
                System.out.println("Elément de menu [" + event.getActionCommand()
                + "] utilisé.");

        placeComponent();
    }

    private void placeComponent() {
        // Création du menu Fichier
        JMenu menu = new JMenu("Fichier"); {
            menu.add(createItem("Nouveau", 'N', afficherMenuListener, CTRL_DOWN_MASK, 'N'));
            menu.insertSeparator(1);
            menu.add(createItem("Sauver", 'S', afficherMenuListener, CTRL_DOWN_MASK, 'S'));
            menu.add(createItem("Ouvrir", 'O', actionEvent ->
                    controller.loadDrawing(FileType.XML, "drawing.xml"), CTRL_DOWN_MASK, 'O'));
            menu.add(createItem("Quitter", 'Q', afficherMenuListener, null, null));
        }

        add(menu);

        // Création du menu Editer (Conversion Noise vers perfect et inversement ?)
        menu = new JMenu("Editer"); {
            menu.add(createItem("Copier", 'C', afficherMenuListener, CTRL_DOWN_MASK, null));
            menu.add(createItem("Coller", 'V', afficherMenuListener, CTRL_DOWN_MASK, null));
            menu.add(createItem("Couper", 'X', afficherMenuListener, CTRL_DOWN_MASK, null));
            menu.insertSeparator(3);
            menu.add(createItem("Undo", 'Z', afficherMenuListener, CTRL_DOWN_MASK, null));
            menu.add(createItem("Redo", 'Y', afficherMenuListener, CTRL_DOWN_MASK, null));
        }

        add(menu);
    }

    private JMenuItem createItem(String text, char keyCode, ActionListener a, Integer keyEvent, Character mnemonic) {
        JMenuItem item = new JMenuItem(text);
        if (mnemonic != null) {
            item.setMnemonic(mnemonic);
        }

        if (keyEvent != null) {
            item.setAccelerator(KeyStroke.getKeyStroke(keyCode, keyEvent, false));
        }
        item.addActionListener(a);
        return  item;
    }
}
