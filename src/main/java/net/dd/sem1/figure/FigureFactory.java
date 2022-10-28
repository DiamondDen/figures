package net.dd.sem1.figure;

import net.dd.sem1.exception.FigureException;

public class FigureFactory {
  public Figure createCircle(int radius) throws FigureException {
    return this.verify(new Circle(radius));
  }

  public Figure createSquare(int length) throws FigureException {
    return this.verify(new Square(length));
  }

  public Figure createRectangle(int width, int height) throws FigureException {
    return this.verify(new Rectangle(width, height));
  }

  private Figure verify(Figure figure) throws FigureException {
    figure.isValid();
    return figure;
  }
}
