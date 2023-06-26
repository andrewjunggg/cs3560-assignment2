package com.andrewjunggg.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.andrewjunggg.DataManager;
import com.andrewjunggg.Group;
import com.andrewjunggg.Identity;
import com.andrewjunggg.User;

public class SideViewPanel extends JPanel {
    private final DataManager dataManager = DataManager.getInstance();
    private JTree tree;

    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));
        buildTree();
    }

    public void buildTree() {
        Group rootGroup = dataManager.getRootGroup();
        DefaultMutableTreeNode root = buildTreeNode(rootGroup);

        tree = new JTree(root);
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                if (value instanceof DefaultMutableTreeNode) {
                    Object userValue = ((DefaultMutableTreeNode) value).getUserObject();

                    // Use common parent class to get ID.
                    if (userValue instanceof Identity)
                        setText(((Identity) userValue).getId());

                    // Groups should always be a directory icon.
                    if (userValue instanceof Group)
                        setIcon(UIManager.getIcon("FileView.directoryIcon"));
                }
                return this;
            }
        });

        removeAll();
        add(tree);
    }

    public void update() {
        buildTree();
        validate();
        repaint();
    }

    public User getSelectedUser() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node != null) {
            Object userObject = node.getUserObject();
            if (userObject instanceof User)
                return (User) userObject;
        }

        return null;
    }

    /**
     * Build the tree nodes from the root group.
     */
    private DefaultMutableTreeNode buildTreeNode(Group group) {
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(group);
        for (User user : group.getUsersArray()) {
            topNode.add(new DefaultMutableTreeNode(user));
        }

        for (Group subgroup : group.getSubgroupsArray()) {
            DefaultMutableTreeNode subgroupNode = buildTreeNode(subgroup);
            topNode.add(subgroupNode);
        }

        return topNode;
    }
}