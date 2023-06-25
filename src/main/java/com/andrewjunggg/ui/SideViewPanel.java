package com.andrewjunggg.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SideViewPanel extends JPanel {
    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("top");
        top.add(new DefaultMutableTreeNode("leaf"));

        JTree tree = new JTree(top);
        add(tree);
    }
}
