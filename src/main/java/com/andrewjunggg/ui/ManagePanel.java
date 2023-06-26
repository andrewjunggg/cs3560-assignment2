package com.andrewjunggg.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagePanel extends JPanel {
    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 300));
        setBorder(BorderFactory.createTitledBorder("Manage"));

        JButton addUserButton = new JButton("Add user");
        JButton addGroupButton = new JButton("Add group");
        JButton userViewButton = new JButton("Open user view");

        add(addUserButton);
        add(addGroupButton);
        add(userViewButton);
    }
}
