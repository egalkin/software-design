package ru.ifmo.egalkin.graph;

import ru.ifmo.egalkin.api.DrawingApi;

import java.util.List;

public class AdjustmentStorageGraph extends CircleStyleGraph {

    public AdjustmentStorageGraph(List<Integer>[] graph, DrawingApi drawingApi) {
        super(graph, drawingApi);

    }

    @Override
    public void drawGraph() {
        super.drawGraph();
        for (int i = 0; i < graph.length; ++i) {
            for (Integer vert : graph[i]) {
                drawingApi.drawLine(vertexNumToCenterPoint.get(i), vertexNumToCenterPoint.get(vert));
            }
        }
    }
}
