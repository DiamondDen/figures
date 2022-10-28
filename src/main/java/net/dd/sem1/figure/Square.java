package net.dd.sem1.figure;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Square extends Figure {
  private final int length;

  @Override
  double calcArea() {
    return this.length * this.length;
  }

  @Override
  void showInfo() {
    System.out.println("Square " + this.length + "x" + this.length);
  }
}
