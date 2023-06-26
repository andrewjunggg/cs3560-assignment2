package com.andrewjunggg.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.andrewjunggg.DataManager;
import com.andrewjunggg.Group;
import com.andrewjunggg.User;

public class SideViewPanel extends JPanel {
    public SideViewPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 500));
        setBorder(BorderFactory.createTitledBorder("View"));

        DataManager dataManager = DataManager.getInstance();
        Group rootGroup = dataManager.getRootGroup();
        DefaultMutableTreeNode root = buildTreeNode(rootGroup);

        JTree top = new JTree(root);
        add(top);
    }

    private DefaultMutableTreeNode buildTreeNode(Group group) {
        DefaultMutableTreeNode topNode = new DefaultMutableTreeNode(group.getId());
        for (User user : group.getUsersArray()) {
            topNode.add(new DefaultMutableTreeNode(user.getId()));
        }

        for (Group subgroup : group.getSubgroupsArray()) {
            DefaultMutableTreeNode subgroupNode = buildTreeNode(subgroup);
            topNode.add(subgroupNode);
        }

        return topNode;
    }
}
