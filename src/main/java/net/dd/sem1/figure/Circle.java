package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

import java.awt.*;

@RequiredArgsConstructor
public class Circle extends Figure {
  private final int radius;

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
    gr.drawOval(x, y, diameter, diameter);
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
