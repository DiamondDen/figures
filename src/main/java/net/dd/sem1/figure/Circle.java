package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;
import net.dd.sem1.gui.util.Position;

import java.awt.*;

@RequiredArgsConstructor
public class Circle extends Figure {
  private final int radius;

  @Override
  public Position centerOffset() {
    return new Position(this.radius, this.radius, 0);
  }

  @Override
  public boolean inArea(int x, int y) {
    return distance(x, y, this.radius, this.radius) < this.radius * this.radius;
  }

  public double distance(
          int x0, int y0,
          int x1, int y1
  ) {
    int diffX = x1 - x0;
    int diffY = y1 - y0;
    return diffY * diffY + diffX * diffX;
  }

  @Override
  public void isValid() throws FigureException {
    if (this.radius < 0)
      throw new TooSmallException(this);
    if (this.radius > 100)
      throw new TooBigException(this);
  }

  @Override
  public void draw(int x, int y, Graphics gr) {
    int diameter = this.radius * 2;
    gr.fillOval(x, y, diameter, diameter);
  }

  @Override
  double calcArea() {
    return Math.PI * this.radius * this.radius;
  }

  @Override
  public String toString() {
    return "Circle with radius " + this.radius;
  }
}
