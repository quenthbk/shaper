package fr.univ.shaper.gui.view;

import fr.univ.shaper.gui.command.*;
import fr.univ.shaper.gui.model.DrawingBoardHandler;
import fr.univ.shaper.util.Contract;
import fr.univ.shaper.xml.DirectorXML;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    static public final int DEFAULT_DRAWING_AREA_HEIGHT = 600;
    static public final int DEFAULT_DRAWING_AREA_WIDTH = 800;

    private final DrawingBoardHandler drawingBoardHandler;

    private final JFileChooser fileChooser = new JFileChooser();

    private final Map<String, JMenuItem> fileItem;

    private final Map<String, JMenuItem>  editItem;

    public Menu(DrawingBoardHandler drawingBoardHandler) {
        Contract.assertThat(drawingBoardHandler != null, "Le drawingBoard ne doit pas être null");
        this.fileItem = new HashMap<>();
        this.editItem = new HashMap<>();
        this.drawingBoardHandler = drawingBoardHandler;

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
                    drawingBoardHandler.createNewDrawingBoard(
                            new Dimension(DEFAULT_DRAWING_AREA_WIDTH, DEFAULT_DRAWING_AREA_HEIGHT));
                });

        fileItem.get(FILE_OPEN).addActionListener(l -> {
            int result = 0;
            result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingBoardHandler.openDrawingBoard(fileChooser.getSelectedFile(), new DirectorXML());
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorHandler("Opération annulée");
            }
        });

        fileItem.get(FILE_SAVE_AS).addActionListener(l -> {
            int result = 0;
            result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingBoardHandler.saveDrawingBoard(fileChooser.getSelectedFile(), new DirectorXML());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorHandler("Opération annulée");
            }
        });

        fileItem.get(FILE_SAVE).addActionListener(l -> {
            if (! drawingBoardHandler.drawIsNew()) {
                try {
                    drawingBoardHandler.saveDrawingBoard();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        drawingBoardHandler.saveDrawingBoard(fileChooser.getSelectedFile(), new DirectorXML());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    errorHandler("Opération de sauvegarde annulée");
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
