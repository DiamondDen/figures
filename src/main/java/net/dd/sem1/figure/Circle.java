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
  void showInfo() {
    System.out.println("Circle with radius " + this.radius);
  }
}
