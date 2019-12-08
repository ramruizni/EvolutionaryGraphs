import data.structures.AdjMatrixEdgeWeightedDigraph;
import data.structures.DirectedEdge;
import data.structures.FloydWarshall;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;

class GraphData {
    AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(60);
    HashMap<String, Integer> weights;
    int[][] coords;
    FloydWarshall FW;
    ArrayList<ArrayList<Integer>> routes = new ArrayList<>();

    GraphData() {
        coords = FileUtils.readVertexCoords();
        weights = FileUtils.readWeights();

        addWeightedEdgesToGraph();

        FW = new FloydWarshall(G); // needed to createRoute
        createRoutes();
        // now we can access the routes
    }

    private void createRoutes() {
        routes.add(createRoute(0, 40)); // MAGENTA
        routes.add(createRoute(5, 45)); // GREEN
        routes.add(createRoute(44, 47));//CYAN
        routes.add(createRoute(14, 41));//RED
        routes.add(createRoute(44, 48));//PINK
        routes.add(createRoute(29, 36));//ORANGE
        createCustomRoutes(routes); // BLACK & YELLOW
    }

    private ArrayList<Integer> createRoute(int start, int end) {
        ArrayList<Integer> r = new ArrayList<>();
        if (FW.hasPath(end, start)) {
            for (DirectedEdge e : FW.path(end, start)) {
                r.add(e.to());
            }
        }
        r.add(end);
        return r;
    }

    private void createCustomRoutes(ArrayList<ArrayList<Integer>> routes) {
        ArrayList<Integer> r7 = new ArrayList<>();
        r7.addAll(createRoute(16, 18));
        ArrayList<Integer> aux = createRoute(18, 29);
        aux.remove(0);
        r7.addAll(aux);
        routes.add(r7);

        ArrayList<Integer> r8 = new ArrayList<>();
        r8.addAll(createRoute(1, 10));
        aux = createRoute(10, 37);
        aux.remove(0);
        r8.addAll(aux);
        aux = createRoute(37, 42);
        aux.remove(0);
        r8.addAll(aux);
        aux = createRoute(42, 46);
        aux.remove(0);
        r8.addAll(aux);
        routes.add(r8);
    }

    private void addWeightedEdgesToGraph() {
        for (String key : weights.keySet()) {
            String[] nodes = key.split(" ");
            int n1 = Integer.parseInt(nodes[0]);
            int n2 = Integer.parseInt(nodes[1]);

            G.addEdge(new DirectedEdge(n1, n2, weights.get(key)));
            G.addEdge(new DirectedEdge(n2, n1, weights.get(key)));
        }
    }
}
