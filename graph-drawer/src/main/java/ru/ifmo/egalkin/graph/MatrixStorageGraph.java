package ru.ifmo.egalkin.graph;

import ru.ifmo.egalkin.api.DrawingApi;

import java.util.List;

public class MatrixStorageGraph extends CircleStyleGraph {

    public MatrixStorageGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);
    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.length; ++i) {
            for (int j = 0; j < graph[i].size(); ++j) {
                if (graph[i].get(j) == 1)
                    drawingApi.drawLine(vertexNumToCenterPoint.get(i), vertexNumToCenterPoint.get(j));
            }
        }
    }
}
