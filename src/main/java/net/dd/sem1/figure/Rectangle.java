package net.dd.sem1.figure;

import com.google.gson.JsonObject;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.exception.InvalidFigureException;

public class Rectangle extends FourCornersFigure {

  public Rectangle(int width, int height) {
    super(width, height);
  }

  @Override
  public JsonObject save() {
    JsonObject jsonObject = super.save();
    jsonObject.addProperty("type", "rectangle");
    return jsonObject;
  }

  @Override
  public void isValid() throws FigureException {
    super.isValid();
    if (this.width == this.height) {
      throw new InvalidFigureException("A rectangle is not a square! ", this);
    }
  }

  @Override
  public String toString() {
    return "Rectangle " + this.width + "x" + this.height + ", area " + this.calcArea();
  }
}
