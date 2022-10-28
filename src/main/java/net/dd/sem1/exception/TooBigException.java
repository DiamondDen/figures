package net.dd.sem1.exception;

import net.dd.sem1.figure.Figure;

public class TooBigException extends FigureException {
  public TooBigException(Figure figure) {
    super(figure);
  }
}
