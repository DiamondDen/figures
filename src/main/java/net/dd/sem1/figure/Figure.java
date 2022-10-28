package net.dd.sem1.figure;

import net.dd.sem1.exception.FigureException;
import net.dd.sem1.gui.render.RenderUnit;

public abstract class Figure implements RenderUnit {
  abstract double calcArea();

  public abstract void isValid() throws FigureException;

  void showInfo() {
    System.out.println(this.toString());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Figure)) return false;
    if (obj == this) return true;
    return ((Figure) obj).calcArea() == this.calcArea();
  }

  public boolean notEquals(Object obj) {
    return !this.equals(obj);
  }

  public boolean moreThan(Object obj) {
    if (!(obj instanceof Figure)) return false;
    if (obj == this) return false;
    return this.calcArea() > ((Figure) obj).calcArea();
  }

  public boolean lessThan(Object obj) {
    if (!(obj instanceof Figure)) return false;
    if (obj == this) return false;
    return this.calcArea() < ((Figure) obj).calcArea();
  }

  public abstract String toString();
}
