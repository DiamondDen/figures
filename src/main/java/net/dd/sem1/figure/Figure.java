package net.dd.sem1.figure;

import net.dd.sem1.exception.FigureException;

public abstract class Figure {
  abstract double calcArea();

  abstract void isValid() throws FigureException;

  void showInfo() {
    System.out.println(this.toString());
  }

  public abstract String toString();
}
