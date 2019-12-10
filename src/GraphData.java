import data.structures.AdjMatrixEdgeWeightedDigraph;
import data.structures.DirectedEdge;
import data.structures.FloydWarshall;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class GraphData {
    AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(60);
    HashMap<String, Integer> weights = FileUtils.readWeights();
    int[][] coords = FileUtils.readVertexCoords();
    FloydWarshall FW;
    ArrayList<ArrayList<Integer>> routes;
    List<Integer> terminalNodes = FileUtils.readTerminalNodes();

    HashMap<String, Integer> edgeHasRoute = new HashMap<>();

    GraphData() {
        addWeightedEdgesToGraph();
        FW = new FloydWarshall(G);
    }

    public void createRandomRoutes() {
        Collections.shuffle(terminalNodes);
        routes = new ArrayList<>();
        int i = 0;
        while (i < 16) {
            routes.add(createRoute(terminalNodes.get(i), terminalNodes.get(i + 1), i / 2));
            i += 2;
        }
    }

    private ArrayList<Integer> createRoute(int start, int end, int index) {
        ArrayList<Integer> route = new ArrayList<>();
        if (FW.hasPath(end, start)) {
            for (DirectedEdge e : FW.path(end, start)) {
                route.add(e.to());
            }
        }
        route.add(end);

        for (int i = 0; i < route.size() - 1; i++) {
            String key = route.get(i) + " " + route.get(i + 1);
            String keyReversed = route.get(i + 1) + " " + route.get(i);

            edgeHasRoute.put(key, index);
            edgeHasRoute.put(keyReversed, index);
        }
        return route;
    }

    private void addWeightedEdgesToGraph() {
        for (String key : weights.keySet()) {
            String[] nodes = key.split(" ");
            int n1 = Integer.parseInt(nodes[0]);
            int n2 = Integer.parseInt(nodes[1]);

            G.addEdge(new DirectedEdge(n1, n2, weights.get(key)));
            G.addEdge(new DirectedEdge(n2, n1, weights.get(key)));

            String keyReversed = n2 + " " + n1;

            if (!edgeHasRoute.containsKey(key)) {

                edgeHasRoute.put(key, -1);
                edgeHasRoute.put(keyReversed, -1);
            }
        }
    }
}
