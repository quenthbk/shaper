package fr.univ.shaper.gui.render;

import fr.univ.shaper.core.GraphicVisitor;
import fr.univ.shaper.core.element.Layer;
import fr.univ.shaper.core.element.noisy.NoisyCircle;
import fr.univ.shaper.core.element.noisy.NoisyLine;
import fr.univ.shaper.core.element.noisy.NoisyRectangle;
import fr.univ.shaper.core.element.perfect.PerfectCircle;
import fr.univ.shaper.core.element.perfect.PerfectLine;
import fr.univ.shaper.core.element.perfect.PerfectRectangle;
import fr.univ.shaper.util.Contract;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Stack;

public class JTreeGraphicVisitor implements GraphicVisitor {

    private final Stack<DefaultMutableTreeNode> stack;

    public JTreeGraphicVisitor(DefaultMutableTreeNode root) {
        Contract.assertThat(root != null, "L'élément racine root ne doit pas être null");
        this.stack = new Stack<>();
        this.stack.push(root);
    }

    @Override
    public void visitLayer(Layer layer) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(layer);
        stack.peek().add(node);
        stack.push(node);
        layer.getChildren().forEach(e -> e.accept(this));
        stack.pop();
    }

    @Override
    public void visitPerfectRectangle(PerfectRectangle element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }

    @Override
    public void visitPerfectCircle(PerfectCircle element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }

    @Override
    public void visitPerfectLine(PerfectLine element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }

    @Override
    public void visitNoisyRectangle(NoisyRectangle element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }

    @Override
    public void visitNoisyCircle(NoisyCircle element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }

    @Override
    public void visitNoisyLine(NoisyLine element) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(element, false);
        stack.peek().add(node);
    }
}