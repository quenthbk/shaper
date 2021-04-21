package fr.univ.shaper.gui.view;

import fr.univ.shaper.gui.controller.DrawController;
import fr.univ.shaper.gui.controller.command.NewDrawCommand;
import fr.univ.shaper.gui.controller.command.OpenFileXmlCommand;
import fr.univ.shaper.gui.controller.command.SaveAsXmlCommand;
import fr.univ.shaper.gui.controller.command.SaveDrawCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.event.KeyEvent.CTRL_DOWN_MASK;

public class Menu extends JMenuBar {

    private final ActionListener afficherMenuListener;

    private final DrawController controller;

    private final JFileChooser fileChooser = new JFileChooser();

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
        OpenFileListener listener = new OpenFileListener();

        JMenu menu = new JMenu("Fichier"); {
            menu.add(createItem("Nouveau", 'N', listener, CTRL_DOWN_MASK, 'N'));
            menu.insertSeparator(1);
            menu.add(createItem("Sauver", 'S', listener, CTRL_DOWN_MASK, 'S'));
            menu.add(createItem("Ouvrir", 'O', listener, CTRL_DOWN_MASK, 'O'));
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

    private class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JMenuItem item = (JMenuItem) actionEvent.getSource();

            int r = JFileChooser.UNDEFINED_CONDITION;

            switch (item.getMnemonic()) {
                case 'O':
                    r = fileChooser.showOpenDialog(getParent());
                    break;
                case 'S':
                    if (controller.fileIsPresent()) {
                        controller.run(new SaveDrawCommand());
                        break;
                    }
                    r = fileChooser.showSaveDialog(getParent());
                    break;
                case 'N':
                    controller.run(new NewDrawCommand());
                    return;
            }

            if (r != JFileChooser.APPROVE_OPTION) {
                System.out.println("Fenêtre FileChooser fermée");
                return;
            }

            switch (item.getMnemonic()) {
                case 'O':
                    File file = fileChooser.getSelectedFile();
                    controller.run(new OpenFileXmlCommand(file));
                    break;
                case 'S':
                    file = fileChooser.getSelectedFile();
                    controller.run(new SaveAsXmlCommand(file));
                    break;
                default:
                    System.out.println("Oups mnmonic inconnu");
            }
        }
    }
}
