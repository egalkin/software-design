package ru.ifmo.egalkin.api;

import ru.ifmo.egalkin.api.model.Point;

public interface DrawingApi {
    int getDrawingAreaWidth();
    int getDrawingAreaHeight();
    void drawCircle(Point center, int radius);
    void drawLine(Point p1, Point p2);
}
