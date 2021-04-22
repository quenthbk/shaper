package fr.univ.shaper.gui.view;

import fr.univ.shaper.core.element.DrawingConstants;
import fr.univ.shaper.gui.model.Pencil;
import fr.univ.shaper.util.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolPanel extends JPanel {

    private static final int BUTTON_SELECT = 0;
    private static final int BUTTON_SHAPE = 1;
    private static final int BUTTON_TYPE = 2;

    private final Map<Integer, List<JButton>> buttons;

    private final Pencil pencil;

    public ToolPanel(Pencil pencil) {
        super(new GridLayout(0, 1));
        Contract.assertThat(pencil != null, "Le model ne doit pas être null");
        this.pencil = pencil;
        buttons = new HashMap<>();
        createView();
        createController();
        placeComponent();
    }

    private void createView() {
        createButton(BUTTON_SELECT, "select", "select");
        createButton(BUTTON_SHAPE, "cercle", DrawingConstants.CIRCLE);
        createButton(BUTTON_SHAPE, "rectangle", DrawingConstants.RECTANGLE);
        createButton(BUTTON_SHAPE, "ligne", DrawingConstants.LINE);
        createButton(BUTTON_TYPE, "Perfect", DrawingConstants.PERFECT);
        createButton(BUTTON_TYPE, "Noisy", DrawingConstants.NOISY);
    }

    private void createController() {
        for (JButton button : buttons.get(BUTTON_SELECT)) {
            button.addActionListener(actionEvent -> {
                JButton j = (JButton) actionEvent.getSource();
                System.out.println("Graphic Tool : " + j.getName());
            });
        }

        for (JButton button : buttons.get(BUTTON_SHAPE)) {
            button.addActionListener(actionEvent -> {
                JButton j = (JButton) actionEvent.getSource();
                System.out.println("Graphic Name : " + j.getName());
                pencil.setGraphicElementName(j.getName());
            });
        }

        for (JButton button : buttons.get(BUTTON_TYPE)) {
            button.addActionListener(actionEvent -> {
                JButton j = (JButton) actionEvent.getSource();
                System.out.println("Graphic Type : " + j.getName());
                pencil.setGraphicElementType(j.getName());
            });
        }
    }

    private void placeComponent() {
        for (int key : buttons.keySet()) {
            for (JButton button : buttons.get(key)) {
                add(button);
            }
        }
    }

    private void createButton(int type, String text, String name) {
        List<JButton> list = buttons.computeIfAbsent(type, k -> new ArrayList<>());
        JButton button = new JButton(text);
        button.setName(name);
        list.add(button);
    }
}
