package fr.univ.shaper.gui.view;

import fr.univ.shaper.file.FileType;
import fr.univ.shaper.gui.controller.DrawController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {

    private ActionListener afficherMenuListener;

    private ActionListener loadFileActionListener;

    private JMenuItem openFile;

    private DrawController controller;

    public Menu(DrawController controller) {
        this.controller = controller;
        // Listener générique qui affiche l'action du menu utilisé
        afficherMenuListener = event ->
                System.out.println("Elément de menu [" + event.getActionCommand()
                + "] utilisé.");

        openFile = new JMenuItem("Ouvrir", 'O');

        configure();
        placeComponent();
    }

    private void placeComponent() {
        // Création du menu Fichier
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem item = new JMenuItem("Nouveau", 'N');
        item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        item = openFile;
        item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        item = new JMenuItem("Sauver", 'S');
        item.addActionListener(afficherMenuListener);
        fichierMenu.insertSeparator(1);
        fichierMenu.add(item);
        item = new JMenuItem("Quitter");
        item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);

        // Création du menu Editer (Conversion Noise vers perfect et inversement ?)
        JMenu editerMenu = new JMenu("Editer");
        item = new JMenuItem("Copier");
        item.addActionListener(afficherMenuListener);
        item.setAccelerator(KeyStroke.getKeyStroke('C',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),
                false));
        editerMenu.add(item);
        item = new JMenuItem("Couper");
        item.addActionListener(afficherMenuListener);
        item.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit()
                .getMenuShortcutKeyMask(), false));
        editerMenu.add(item);
        item = new JMenuItem("Coller");
        item.addActionListener(afficherMenuListener);
        item.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit()
                .getMenuShortcutKeyMask(), false));
        editerMenu.add(item);

        // Création du menu Divers
        JMenu diversMenu = new JMenu("Divers");
        JMenu sousMenuDiver1 = new JMenu("Sous menu 1");

        item.addActionListener(afficherMenuListener);
        item = new JMenuItem("Sous menu 1 1");
        sousMenuDiver1.add(item);
        item.addActionListener(afficherMenuListener);
        JMenu sousMenuDivers2 = new JMenu("Sous menu 1 2");
        item = new JMenuItem("Sous menu 1 2 1");
        sousMenuDivers2.add(item);
        sousMenuDiver1.add(sousMenuDivers2);

        diversMenu.add(sousMenuDiver1);
        item = new JCheckBoxMenuItem("Validé");
        diversMenu.add(item);
        item.addActionListener(afficherMenuListener);
        diversMenu.addSeparator();
        ButtonGroup buttonGroup = new ButtonGroup();
        item = new JRadioButtonMenuItem("Cas 1");
        diversMenu.add(item);
        item.addActionListener(afficherMenuListener);
        buttonGroup.add(item);
        item = new JRadioButtonMenuItem("Cas 2");
        diversMenu.add(item);
        item.addActionListener(afficherMenuListener);
        buttonGroup.add(item);
        diversMenu.addSeparator();
        diversMenu.add(item = new JMenuItem("Autre",
                new ImageIcon("about_32.png")));
        item.addActionListener(afficherMenuListener);

        // ajout des menus à la barre de menus
        add(fichierMenu);
        add(editerMenu);
        add(diversMenu);
    }

    public void configure() {
        openFile.addActionListener(actionEvent ->
            controller.loadDrawing(FileType.XML, "drawing.xml")
        );
    }
}
