package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.TooBigException;
import net.dd.sem1.exception.TooSmallException;

import java.awt.*;

@RequiredArgsConstructor
public abstract class FourCornersFigure extends Figure {
  protected final int width, height;

  @Override
  public void isValid() throws FigureException {
    if (this.width < 0 || this.height < 0)
      throw new TooSmallException(this);
    if (this.width > 100 || this.height > 100)
      throw new TooBigException(this);
  }

  @Override
  public void draw(int x, int y, Graphics gr) {
    gr.fillRect(x, y, this.width, this.height);
  }

  @Override
  double calcArea() {
    return this.width * height;
  }
}
