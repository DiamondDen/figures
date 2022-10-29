package net.dd.sem1.figure;

import com.google.gson.JsonObject;

public class Square extends FourCornersFigure {
  public Square(int length) {
    super(length, length);
  }

  @Override
  public JsonObject save() {
    JsonObject jsonObject = super.save();
    jsonObject.addProperty("type", "square");
    return jsonObject;
  }

  @Override
  public String toString() {
    return "Square " + this.width + "x" + this.width + ", area " + this.calcArea();
  }
}
