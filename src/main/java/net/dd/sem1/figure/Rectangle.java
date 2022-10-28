package net.dd.sem1.figure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

@RequiredArgsConstructor
public class Rectangle extends Figure {
  private final int width, height;

  @Override
  void isValid() throws FigureException {
    if (this.width < 0 || this.height < 0)
      throw new TooSmallException(this);
    if (this.width > 100 || this.height > 100)
      throw new TooBigException(this);
  }

  @Override
  double calcArea() {
    return this.width * height;
  }

  @Override
  public String toString() {
    return "Rectangle " + this.width + "x" + this.height;
  }
}
