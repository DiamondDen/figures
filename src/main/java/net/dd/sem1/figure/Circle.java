package net.dd.sem1.figure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

@RequiredArgsConstructor
public class Circle extends Figure {
  private final int radius;

  @Override
  void isValid() throws FigureException {
    if (this.radius < 0)
      throw new TooSmallException(this);
    if (this.radius > 100)
      throw new TooBigException(this);
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
