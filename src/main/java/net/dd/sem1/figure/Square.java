package net.dd.sem1.figure;

public class Square extends FourCornersFigure {
  public Square(int length) {
    super(length, length);
  }

  @Override
  public String toString() {
    return "Square " + this.width + "x" + this.width;
  }
}
