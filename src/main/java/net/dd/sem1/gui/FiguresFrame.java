package net.dd.sem1.gui;

import javax.swing.*;

public class FiguresFrame extends JFrame {
  public FiguresFrame() {
    this.add(new FiguresPanel());
    this.setTitle("Figures");
    this.setSize(480, 350);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
