package net.dd.sem1.figure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.InvalidFigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

import java.awt.*;

public class Rectangle extends FourCornersFigure {

  public Rectangle(int width, int height) {
    super(width, height);
  }

  @Override
  public void isValid() throws FigureException {
    super.isValid();
    if (this.width == this.height) {
      throw new InvalidFigureException("A rectangle is not a square! ", this);
    }
  }

  @Override
  public void draw(int x, int y, Graphics gr) {
    gr.fillRect(x, y, this.width, this.height);
  }

  @Override
  double calcArea() {
    return this.width * height;
  }

  @Override
  public String toString() {
    return "Rectangle " + this.width + "x" + this.height + ", area " + this.calcArea();
  }
}
