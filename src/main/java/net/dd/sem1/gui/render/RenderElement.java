package net.dd.sem1.gui.render;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.dd.sem1.gui.util.Position;

import java.awt.*;

@RequiredArgsConstructor
@Setter
@Getter
public class RenderElement implements RenderUnit, ColoredUnit, SpatialUnit {

  private final RenderUnit figure;
  private Color color;
  private Position position;

  @Override
  public void draw(int globalX, int globalY, Graphics gr) {
    gr.setColor(this.color);
    this.figure.draw(
            this.position.getX() + globalX,
            this.position.getY() + globalY,
            gr
    );
  }
}
