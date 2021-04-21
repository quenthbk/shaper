package fr.univ.shaper.gui.view;


import fr.univ.shaper.gui.controller.DrawController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class LayerPanel extends JPanel {


    private JTree tree;

    public LayerPanel(DrawController controller) {
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Layer principale");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        //create the tree by passing in the root node
        tree = new JTree(root);
        add(tree);
    }
}
