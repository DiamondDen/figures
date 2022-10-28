package net.dd.sem1.gui;

import com.google.gson.Gson;
import net.dd.sem1.exception.FigureException;
import net.dd.sem1.figure.Figure;
import net.dd.sem1.figure.FigureFactory;
import net.dd.sem1.gui.render.*;
import net.dd.sem1.gui.util.Position;
import net.dd.sem1.gui.util.ThrowingSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FiguresPanel extends JPanel {

  private final List<Unit> elementList = new ArrayList<>();

  private final Random r = new Random();

  private int lastId;

  private int mouseX, mouseY;
  private boolean mousePressed;

  public FiguresPanel() {
    FigureFactory figureFactory = new FigureFactory();
    this.addCreateButton("Circle", () -> figureFactory.createCircle(r.nextInt(58) + 3));
    this.addCreateButton("Square", () -> figureFactory.createSquare(r.nextInt(58) + 3));
    this.addCreateButton("Rectangle", () -> figureFactory.createRectangle(r.nextInt(58) + 3, r.nextInt(58) + 3));

    MouseAdapter mouseAdapter = new MouseAdapter() {

      @Override
      public void mouseReleased(MouseEvent e) {
        mousePressed = false;
      }

      @Override
      public void mousePressed(MouseEvent e) {
        mousePressed = true;
        Unit unit = getCaptured(e.getX(), e.getY());
        if (unit == null) return;

        switch (e.getButton()) {
          case 1:
            if (unit instanceof MovedUnit) {
              ((MovedUnit) unit).onCaptured(mouseX, mouseY);
              if (unit instanceof SpatialUnit) {
                ((SpatialUnit) unit).getPosition().setZ(lastId++);
              }
            }
            break;
          case 3:
            if (unit instanceof ColoredUnit) {
              ((ColoredUnit) unit).setColor(randomColor());
            }
            break;

          default:
            return;
        }
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        mouseX = mouseY = -1;
      }
    };
    this.addMouseListener(mouseAdapter);
    this.addMouseMotionListener(mouseAdapter);

    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::repaint, 0, 15, TimeUnit.MILLISECONDS);
  }

  public Unit getCaptured(int x, int y) {
    List<Unit> captured = new ArrayList<>();

    for (Unit unit : this.elementList) {
      if (unit instanceof AreaUnit && ((AreaUnit) unit).inArea(x, y)) {
        captured.add(unit);
      }
    }

    if (captured.isEmpty())
      return null;

    captured.sort(null);
    return captured.get(0);
  }

  private void addCreateButton(String name, ThrowingSupplier<Figure> supplier) {
    JButton jButton = new JButton(name);
    jButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          Figure figure = supplier.get();
          addFigureWithRandomColorAndPosition(figure);
        } catch (FigureException figureException) {
          System.err.println(figureException.getMessage());
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    });
    this.add(jButton);
  }

  private Color randomColor() {
    return new Color(
            r.nextInt(256),
            r.nextInt(256),
            r.nextInt(256)
    );
  }

  private void addFigureWithRandomColorAndPosition(Figure figure) {
    RenderElement renderElement = new RenderElement(figure);
    renderElement.setColor(this.randomColor());

    Position centerOffset = figure.centerOffset();
    int windowWidth = this.getWidth() - centerOffset.getX();
    int windowHeight = this.getHeight() - centerOffset.getY();

    int x = r.nextInt(windowWidth);
    int y = r.nextInt(windowHeight);

    renderElement.setPosition(new Position(x, y, this.lastId++));
    elementList.add(renderElement);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.elementList.sort(null);
    Collections.reverse(this.elementList);

    for (Unit unit : this.elementList) {
      if (unit instanceof MovedUnit) {
        if (this.mousePressed && ((MovedUnit) unit).isCaptured()) {
          ((MovedUnit) unit).onCapturedUpdate(this.mouseX, this.mouseY);
        } else {
          ((MovedUnit) unit).resetCaptured();
        }
      }
      if (!(unit instanceof RenderUnit)) continue;
      ((RenderUnit) unit).draw(0, 0, g);
    }

    Unit hoverUnit = this.getCaptured(this.mouseX, this.mouseY);
    if (hoverUnit != null) {
      int windowWidth = this.getWidth();

      g.setColor(Color.GRAY);

      String desc = hoverUnit.toString();

      String[] lines = desc.split("\n");
      int stringWidth = 0;
      for (String line : lines) {
        stringWidth = Math.max(stringWidth, g.getFontMetrics().stringWidth(line));
      }
      int stringHeight = g.getFontMetrics().getHeight();

      int hoverTextX = this.mouseX + 10;
      int hoverTextY = this.mouseY;
      if (hoverTextX + stringWidth + 10 > windowWidth) {
        hoverTextX -= stringWidth + 25;
      }
      g.fillRect(hoverTextX, hoverTextY, stringWidth + 10, stringHeight * lines.length + 7);

      g.setColor(Color.WHITE);
      for (String line : lines) {
        g.drawString(line, hoverTextX + 5, hoverTextY + stringHeight);
        hoverTextY += stringHeight;
      }
    }
  }
}
