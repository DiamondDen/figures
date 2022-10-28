package net.dd.sem1.gui.render;

public interface MovedUnit {
  void onCaptured(int x, int y);

  void resetCaptured();

  void onCapturedUpdate(int x, int y);

  boolean isCaptured();
}
