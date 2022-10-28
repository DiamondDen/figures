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
  void showInfo() {
    System.out.println("Rectangle " + this.width + "x" + this.height);
  }
}
