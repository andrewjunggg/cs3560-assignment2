package com.andrewjunggg.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ControlPanel {
    private final JFrame frame;

    public ControlPanel() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 800);

        frame.add(new SideViewPanel(), BorderLayout.WEST);
        frame.add(new ManagePanel(), BorderLayout.EAST);
    }

    public void show() {
        frame.setVisible(true);
    }
}
