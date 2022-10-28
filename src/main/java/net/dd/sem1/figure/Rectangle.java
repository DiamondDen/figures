package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rectangle extends Figure {
  private final int width, height;

  @Override
  double calcArea() {
    return this.width * height;
  }

  @Override
  public String toString() {
    return "Rectangle " + this.width + "x" + this.height;
  }
}
