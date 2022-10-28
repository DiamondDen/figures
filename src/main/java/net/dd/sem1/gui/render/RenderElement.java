package net.dd.sem1.gui.render;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dd.sem1.gui.util.Position;

import java.awt.*;

@RequiredArgsConstructor
@Setter
@Getter
public class RenderElement implements Unit, AreaUnit, RenderUnit, ColoredUnit, SpatialUnit, MovedUnit, Comparable<Unit> {

  private final Unit figure;
  private Color color;
  private Position position;

  private transient int capturedOffsetX, capturedOffsetY;

  @Override
  public void onCaptured(int x, int y) {
    this.capturedOffsetX = this.position.getX() - x;
    this.capturedOffsetY = this.position.getY() - y;
  }

  @Override
  public void resetCaptured() {
    this.capturedOffsetX = this.capturedOffsetY = -1;
  }

  @Override
  public void onCapturedUpdate(int x, int y) {
    if (x < 0) x = 0;
    if (y < 0) y = 0;
    this.position.setX(x + this.capturedOffsetX);
    this.position.setY(y + this.capturedOffsetY);
  }

  @Override
  public boolean isCaptured() {
    return this.capturedOffsetX != -1 && this.capturedOffsetY != -1;
  }

  @Override
  public boolean inArea(int x, int y) {
    if (!(this.figure instanceof AreaUnit)) {
      return false;
    }

    int offsetX = x - position.getX();
    int offsetY = y - position.getY();

    if (offsetX < 0 || offsetY < 0)
      return false;

    return ((AreaUnit) this.figure).inArea(offsetX, offsetY);
  }

  @Override
  public void draw(int globalX, int globalY, Graphics gr) {
    gr.setColor(this.color);
    if (this.figure instanceof RenderUnit) {
      ((RenderUnit) this.figure).draw(
              this.position.getX() + globalX,
              this.position.getY() + globalY,
              gr
      );
    }
  }

  @Override
  public int compareTo(Unit o) {
    if (!(o instanceof SpatialUnit))
      return 0;
    return Integer.compare(((SpatialUnit) o).getPosition().getZ(), this.position.getZ());
  }

  @Override
  public String toString() {
    return this.figure.toString() + ":\nX"
      + this.position.getX() + ", Y: " + this.position.getY();
  }
}
