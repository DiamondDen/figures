package net.dd.sem1.gui;

import net.dd.sem1.exception.FigureException;
import net.dd.sem1.figure.Figure;
import net.dd.sem1.figure.FigureFactory;
import net.dd.sem1.gui.render.RenderElement;
import net.dd.sem1.gui.render.RenderUnit;
import net.dd.sem1.gui.util.Position;
import net.dd.sem1.gui.util.ThrowingSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class FiguresPanel extends JPanel {

  private final List<RenderUnit> elementList = new ArrayList<>();

  private final Random r = new Random();

  private int lastId;

  public FiguresPanel() {
    FigureFactory figureFactory = new FigureFactory();
    this.addCreateButton("Circle", () -> figureFactory.createCircle(r.nextInt(58) + 3));
    this.addCreateButton("Square", () -> figureFactory.createSquare(r.nextInt(58) + 3));
    this.addCreateButton("Rectangle", () -> figureFactory.createRectangle(r.nextInt(58) + 3, r.nextInt(58) + 3));

    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::repaint, 0, 15, TimeUnit.MILLISECONDS);
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

  private void addFigureWithRandomColorAndPosition(Figure figure) {
    RenderElement renderElement = new RenderElement(figure);
    renderElement.setColor(new Color(
            r.nextInt(256),
            r.nextInt(256),
            r.nextInt(256)
    ));
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

    for (RenderUnit renderUnit : this.elementList) {
      renderUnit.draw(0, 0, g);
    }
  }
}
