package net.dd.sem1.exception;

import net.dd.sem1.figure.Figure;

public class InvalidFigureException extends FigureException {
  public InvalidFigureException(String message ,Figure figure) {
    super(message, figure);
  }
}
