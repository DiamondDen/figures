package net.dd.sem1.exception;

import net.dd.sem1.figure.Figure;

public class TooSmallException extends FigureException {

  public TooSmallException(Figure figure) {
    super(figure);
  }
}