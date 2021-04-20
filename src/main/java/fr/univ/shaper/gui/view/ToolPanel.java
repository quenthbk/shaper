package fr.univ.shaper.gui.view;

import fr.univ.shaper.gui.controller.DrawController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {

    private final JButton selectCircleButton;

    private final JButton selectRectangleButton;

    private final JButton selectLineButton;

    private final JButton selectLayerButton;

    private final JButton selectPerfectButton;

    private final JButton selectNoisyButton;

    private final ActionListener actionSelectName;

    private final ActionListener actionSelectType;

    private final DrawController controller;

    public ToolPanel(DrawController controller) {
        super(new GridLayout(0, 1));
        this.controller = controller;
        actionSelectName = actionEvent -> {
            JButton j = (JButton) actionEvent.getSource();
            System.out.println("Graphic Name : " + j.getName());
            controller.selectGraphicElementName(j.getName());
        };

        actionSelectType = actionEvent -> {
            JButton j = (JButton) actionEvent.getSource();
            System.out.println("Graphic Type : " + j.getName());
            controller.selectGraphicElementType(j.getName());
        };

        selectCircleButton = new JButton("circle");
        selectCircleButton.setName("circle");
        selectCircleButton.addActionListener(actionSelectName);

        selectRectangleButton = new JButton("rectangle");
        selectRectangleButton.setName("rectangle");
        selectRectangleButton.addActionListener(actionSelectName);

        selectLineButton = new JButton("line");
        selectLineButton.setName("line");
        selectLineButton.addActionListener(actionSelectName);

        selectLayerButton = new JButton("layer");
        selectLayerButton.setName("layer");
        selectLayerButton.addActionListener(actionSelectName);

        selectPerfectButton = new JButton("perfect");
        selectPerfectButton.setName("perfect");
        selectPerfectButton.addActionListener(actionSelectType);

        selectNoisyButton = new JButton("noisy");
        selectNoisyButton.setName("noisy");
        selectNoisyButton.addActionListener(actionSelectType);

        placeComponent();
    }

    private void placeComponent() {
        add(selectCircleButton);
        add(selectRectangleButton);
        add(selectLineButton);
        add(selectLayerButton);

        add(selectPerfectButton);
        add(selectNoisyButton);
    }
}
