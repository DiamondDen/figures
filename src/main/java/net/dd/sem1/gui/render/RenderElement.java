package net.dd.sem1.gui.render;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dd.sem1.gui.util.Position;

import java.awt.*;

@RequiredArgsConstructor
@Setter
@Getter
public class RenderElement implements Unit, MovedUnit, RenderUnit, ColoredUnit, SpatialUnit, Comparable<Unit> {

  private final Unit figure;
  private Color color;
  private Position position;

  @Override
  public boolean inArea(int x, int y) {
    if (!(this.figure instanceof MovedUnit)) {
      return false;
    }

    int offsetX = x - position.getX();
    int offsetY = y - position.getY();

    if (offsetX < 0 || offsetY < 0)
      return false;

    return ((MovedUnit) this.figure).inArea(offsetX, offsetY);
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
