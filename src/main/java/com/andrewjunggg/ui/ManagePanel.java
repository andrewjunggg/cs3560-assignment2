package com.andrewjunggg.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ManagePanel extends JPanel {
    public ManagePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Manage"));
    }
}
