package net.dd.sem1.exception;

import net.dd.sem1.figure.Figure;

public class FigureException extends Exception {
  public FigureException(Figure figure) {
    super(figure.toString());
  }
}
