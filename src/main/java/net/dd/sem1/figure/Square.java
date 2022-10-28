package net.dd.sem1.figure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

@RequiredArgsConstructor
public class Square extends Figure {
  private final int length;

  @Override
  void isValid() throws FigureException {
    if (this.length < 0)
      throw new TooSmallException(this);
    if (this.length > 100)
      throw new TooBigException(this);
  }

  @Override
  double calcArea() {
    return this.length * this.length;
  }

  @Override
  public String toString() {
    return "Square " + this.length + "x" + this.length;
  }
}
