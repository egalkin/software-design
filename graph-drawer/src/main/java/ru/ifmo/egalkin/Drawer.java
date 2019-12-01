package ru.ifmo.egalkin;

import javafx.application.Application;
import ru.ifmo.egalkin.api.AwtDrawingApi;
import ru.ifmo.egalkin.api.DrawingApi;
import ru.ifmo.egalkin.api.JavaFxDrawingApi;
import ru.ifmo.egalkin.graph.AdjustmentStorageGraph;
import ru.ifmo.egalkin.graph.Graph;
import ru.ifmo.egalkin.graph.MatrixStorageGraph;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Drawer {

    private static List<Integer>[] g;

    public static void main(String... args) {
        if (args.length != 2) {
            System.out.println("Usage:java Drawer <library> <storage_type>");
        } else {
            String library = args[0];
            String storageType = args[1];
            DrawingApi api = library.equals("awt") ? new AwtDrawingApi(600, 600) : new JavaFxDrawingApi();
            if (storageType.equals("adjustment")) {
                readAdjustmentGraph();
            } else {
                readMatrixGraph();
            }
            Graph graph = storageType.equals("adjustment") ? new AdjustmentStorageGraph(g, api) : new MatrixStorageGraph(g, api);
            if (library.equals("awt")) {
                graph.drawGraph();
                AwtDrawingApi frameApi = (AwtDrawingApi) api;
                frameApi.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });
                frameApi.setSize(frameApi.getDrawingAreaWidth(), frameApi.getDrawingAreaHeight());
                frameApi.setVisible(true);
                frameApi.setResizable(false);
                frameApi.setTitle("Graph visualizer");
            } else {
                JavaFxDrawingApi.dumpState();
                graph.drawGraph();
                Application.launch(JavaFxDrawingApi.class);
            }
        }
    }


    private static void readMatrixGraph() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i].add(in.nextInt());
            }
        }
    }

    private static void readAdjustmentGraph() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            int u,v;
            u = in.nextInt();
            v = in.nextInt();
            u--; v--;
            g[u].add(v);
        }
    }
}
