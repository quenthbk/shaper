package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.command.NewCommand;
import fr.univ.shaper.gui.command.OpenFileXmlCommand;
import fr.univ.shaper.gui.command.SaveAsXmlCommand;
import fr.univ.shaper.gui.command.SaveCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.event.KeyEvent.CTRL_DOWN_MASK;

public class Menu extends JMenuBar {

    private final ActionListener afficherMenuListener;

    private final DrawingBoard controller;

    private final JFileChooser fileChooser = new JFileChooser();

    public Menu(DrawingBoard controller) {
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

        private final GraphicBuilder builder = new DefaultGraphicBuilder(
                GraphicFactoryHandler.newInstance()
        );

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JMenuItem item = (JMenuItem) actionEvent.getSource();

            int r = JFileChooser.UNDEFINED_CONDITION;

            switch (item.getMnemonic()) {
                case 'O':
                    r = fileChooser.showOpenDialog(getParent());
                    break;
                case 'S':
                    if (! controller.isNew()) {
                        controller.run(new SaveCommand());
                        break;
                    }
                    r = fileChooser.showSaveDialog(getParent());
                    break;
                case 'N':
                    controller.run(new NewCommand());
                    return;
            }

            if (r != JFileChooser.APPROVE_OPTION) {
                System.out.println("Fenêtre FileChooser fermée");
                return;
            }

            switch (item.getMnemonic()) {
                case 'O':
                    File file = fileChooser.getSelectedFile();
                    controller.run(new OpenFileXmlCommand(file, builder));
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
