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
import com.andrewjunggg.User;

public class SideViewPanel extends JPanel {

    private JTree tree;

    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));
        buildTree();
    }

    public void buildTree() {
        DataManager dataManager = DataManager.getInstance();
        Group rootGroup = dataManager.getRootGroup();
        DefaultMutableTreeNode root = buildTreeNode(rootGroup);

        tree = new JTree(root);
        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, 
                                        boolean expanded, boolean leaf, int row, boolean hasFocus) {
                
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                if (value instanceof DefaultMutableTreeNode) {
                    // Set the name of the tree cell to the ID if it is a user or group.
                    // TODO: replace with OOP method, common parent class

                    Object userValue = ((DefaultMutableTreeNode) value).getUserObject();

                    if (userValue instanceof User) {
                        setText(((User) userValue).getId());
                    }

                    if (userValue instanceof Group) {
                        setText(((Group) userValue).getId());
                        setIcon(UIManager.getIcon("FileView.directoryIcon"));
                        
                    }
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
        
        if (node == null)
            return null;

        Object userObject = node.getUserObject();

        if (userObject instanceof User)
            return (User) userObject;

        return null;
    }

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
