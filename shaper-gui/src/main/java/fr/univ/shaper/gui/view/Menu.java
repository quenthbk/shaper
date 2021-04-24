package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.DefaultGraphicBuilder;
import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.gui.model.DrawingBoard;
import fr.univ.shaper.gui.command.NewCommand;
import fr.univ.shaper.gui.command.OpenFileXmlCommand;
import fr.univ.shaper.gui.command.SaveAsXmlCommand;
import fr.univ.shaper.gui.command.SaveCommand;
import fr.univ.shaper.util.Contract;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
import static java.awt.event.KeyEvent.CTRL_DOWN_MASK;

public class Menu extends JMenuBar {

    static private final int TYPE_FILE = 0;
    static private final int TYPE_EDITING = 1;

    static private final String FILE_NEW = "Nouveau";
    static private final String FILE_SAVE = "Sauver";
    static private final String FILE_SAVE_AS = "Sauver vers..";
    static private final String FILE_OPEN = "Ouvrir";
    static private final String FILE_QUIT = "Quitter";

    static private final String EDIT_COPY = "Copier";
    static private final String EDIT_PAST = "Coller";
    static private final String EDIT_CUT = "Couper";
    static private final String EDIT_UNDO = "Retour";

    private final DrawingBoard drawingBoard;

    private final JFileChooser fileChooser = new JFileChooser();

    private final Map<String, JMenuItem> fileItem;

    private final Map<String, JMenuItem>  editItem;

    public Menu(DrawingBoard drawingBoard) {
        Contract.assertThat(drawingBoard != null, "Le drawingBoard ne doit pas être null");
        this.fileItem = new HashMap<>();
        this.editItem = new HashMap<>();
        this.drawingBoard = drawingBoard;

        createViewAndPlaceComponent();
        createController();
    }

    // ------------------------------------------------------ //
    //                         VIEW                           //
    // ------------------------------------------------------ //

    private void createViewAndPlaceComponent() {
        JMenu menu = new JMenu("fichier"); {
            menu.add(createItem(TYPE_FILE, FILE_NEW, 'N', CTRL_DOWN_MASK, 'N'));
            menu.insertSeparator(1);
            menu.add(createItem(TYPE_FILE, FILE_OPEN, 'O', CTRL_DOWN_MASK, 'O'));
            menu.add(createItem(TYPE_FILE, FILE_SAVE, 'S', CTRL_DOWN_MASK, 'S'));
            menu.add(createItem(TYPE_FILE, FILE_SAVE_AS, 'S', null, null));
            menu.insertSeparator(5);
            menu.add(createItem(TYPE_FILE, FILE_QUIT, 'Q', null, null));
        }
        add(menu);
        menu = new JMenu("Édition"); {
            menu.add(createItem(TYPE_EDITING, EDIT_COPY, 'C', CTRL_DOWN_MASK, null));
            menu.add(createItem(TYPE_EDITING, EDIT_PAST, 'V', CTRL_DOWN_MASK, null));
            menu.add(createItem(TYPE_EDITING, EDIT_CUT, 'X', CTRL_DOWN_MASK, null));
            menu.insertSeparator(3);
            menu.add(createItem(TYPE_EDITING, EDIT_UNDO, 'Z', CTRL_DOWN_MASK, null));
        }
        add(menu);
    }

    // ------------------------------------------------------ //
    //                      CONTROLLER                        //
    // ------------------------------------------------------ //

    private void createController() {
        fileItem
                .get(FILE_NEW)
                .addActionListener(l -> {
            drawingBoard.run(new NewCommand());
        });

        fileItem.get(FILE_OPEN).addActionListener(l -> {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                drawingBoard.run(new OpenFileXmlCommand(fileChooser.getSelectedFile(),
                        new DefaultGraphicBuilder(GraphicFactoryHandler.newInstance())));
            } else {
                errorHandler("Opération annulée");
            }
        });

        fileItem.get(FILE_SAVE_AS).addActionListener(l -> {
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                drawingBoard.run(new SaveAsXmlCommand(fileChooser.getSelectedFile()));
            } else {
                errorHandler("Opération annulée");
            }
        });

        fileItem.get(FILE_SAVE).addActionListener(l -> {
            if (! drawingBoard.isNew()) {
                drawingBoard.run(new SaveCommand());
            } else {
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    drawingBoard.run(new SaveAsXmlCommand(fileChooser.getSelectedFile()));
                } else {
                    errorHandler("Opération annulée");
                }
            }
        });
    }

    // --------------------------------------------------------- //
    //                          OUTILS                           //
    // --------------------------------------------------------- //

    private void errorHandler(String message) {
        System.err.println(message);
    }

    private JMenuItem createItem(int type, String text, char keyCode, Integer keyEvent, Character mnemonic) {
        Map<String, JMenuItem> menu;
        switch (type) {
            case TYPE_FILE:
                menu = fileItem;
                break;
            case TYPE_EDITING:
                menu = editItem;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        JMenuItem item = new JMenuItem(text);
        menu.put(text, item);

        if (mnemonic != null) {
            item.setMnemonic(mnemonic);
        }

        if (keyEvent != null) {
            item.setAccelerator(KeyStroke.getKeyStroke(keyCode, keyEvent, false));
        }

        return item;
    }
}
