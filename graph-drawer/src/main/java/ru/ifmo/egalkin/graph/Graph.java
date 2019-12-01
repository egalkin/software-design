package ru.ifmo.egalkin.graph;

import ru.ifmo.egalkin.api.DrawingApi;

public abstract class Graph {

    protected DrawingApi drawingApi;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void drawGraph();
}
