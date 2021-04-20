package fr.univ.shaper.gui.view;

import fr.univ.shaper.gui.controller.DrawController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {

    public ToolPanel(DrawController controller) {
        super(new GridLayout(0, 1));

        // ------------------------------------------------- //
        //               SELECT GRAPHIC ELEMENT              //
        // ------------------------------------------------- //

        ActionListener actionSelectName = actionEvent -> {
            JButton j = (JButton) actionEvent.getSource();
            System.out.println("Graphic Name : " + j.getName());
            controller.selectGraphicElementName(j.getName());
        };

        add(createButton("circle", "circle", actionSelectName));
        add(createButton("rectangle", "rectangle", actionSelectName));
        add(createButton("line", "line", actionSelectName));
        add(createButton("layer", "layer", actionSelectName));

        // ------------------------------------------------- //
        //               SELECT GRAPHIC TYPE                 //
        // ------------------------------------------------- //

        ActionListener actionSelectType = actionEvent -> {
            JButton j = (JButton) actionEvent.getSource();
            System.out.println("Graphic Type : " + j.getName());
            controller.selectGraphicElementType(j.getName());
        };

        add(createButton("perfect", "perfect", actionSelectType));
        add(createButton("noisy", "noisy", actionSelectType));
    }

    private JButton createButton(String text, String id, ActionListener act) {
        JButton button = new JButton(text);
        button.setName(id);
        button.addActionListener(act);
        return button;
    }

}
