package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Circle extends Figure {
  private final int radius;

  @Override
  double calcArea() {
    return Math.PI * this.radius * this.radius;
  }

  @Override
  public String toString() {
    return "Circle with radius " + this.radius;
  }
}
