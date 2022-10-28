package net.dd.sem1;

import com.google.gson.Gson;
import net.dd.sem1.gui.FiguresFrame;

public class Main {

  public static final Gson gson = new Gson();

  public static void main(String[] args) {
    new FiguresFrame();
  }
}
