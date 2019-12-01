package ru.ifmo.egalkin.api;


import ru.ifmo.egalkin.api.model.Circle;
import ru.ifmo.egalkin.api.model.Line;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawingApi extends Frame implements DrawingApi {

    private final int drawingAreaWidth;
    private final int drawingAreaHeight;

    private List<Circle> circlesToDraw = new ArrayList<>();
    private List<Line> linesToDraw = new ArrayList<>();


    public AwtDrawingApi(int drawingAreaWidth, int drawingAreaHeight) throws HeadlessException {
        super();
        this.drawingAreaWidth = drawingAreaWidth;
        this.drawingAreaHeight = drawingAreaHeight;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D)g;
        for (int i = 0; i < circlesToDraw.size(); ++i) {
            Circle circle = circlesToDraw.get(i);
            ru.ifmo.egalkin.api.model.Point leftCorner = circle.getRectangleLeftCornen();
            int radius = circle.getRadius();
            ga.setPaint(Color.GREEN);
            ga.fill(new Ellipse2D.Double(leftCorner.x, leftCorner.y, radius * 2, radius * 2));
            ga.setPaint(Color.BLACK);
            ga.drawString(i+1+"", leftCorner.x + radius, leftCorner.y + radius);
        }
        linesToDraw.forEach(line -> {
            ru.ifmo.egalkin.api.model.Point p1,p2;
            p1 = line.getP1();
            p2 = line.getP2();
            ga.drawLine(p1.x, p1.y, p2.x, p2.y);
        });
    }

    @Override
    public int getDrawingAreaWidth() {
        return this.drawingAreaWidth;
    }

    @Override
    public int getDrawingAreaHeight() {
        return this.drawingAreaHeight;
    }

    @Override
    public void drawCircle(ru.ifmo.egalkin.api.model.Point center, int radius) {
        circlesToDraw.add(new Circle(center, radius));
    }

    @Override
    public void drawLine(ru.ifmo.egalkin.api.model.Point p1, ru.ifmo.egalkin.api.model.Point p2) {
        linesToDraw.add(new Line(p1, p2));
    }
}
