package net.dd.sem1.figure;

public abstract class Figure {
  abstract double calcArea();

  void showInfo() {
    System.out.println(this.toString());
  }

  public abstract String toString();
}
