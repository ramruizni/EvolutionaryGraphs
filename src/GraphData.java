import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class GraphData {

    private AdjMatrixEdgeWeightedDigraph G;
    private int[][] coord;
    private FloydWarshall FW;
    private ArrayList<ArrayList<Integer>> routes;
    private DirectedEdge[][] adj;

    GraphData(HashMap<String, Double> W, ArrayList<String> edgeTitles,
              String month, String day, String hour) {
        G = new AdjMatrixEdgeWeightedDigraph(60);
        coord = new int[60][2];
        adj = G.getAdj();
        inputData();
        routes = new ArrayList<>();

        setLearnedWeights(W, edgeTitles, month, day, hour);

        FW = new FloydWarshall(G); // needed to createRoute
        createRoutes();
        // now we can access the routes
    }

    private void createRoutes(){
        routes.add(createRoute(0, 40)); // MAGENTA
        routes.add(createRoute(5, 45)); // GREEN
        routes.add(createRoute(44, 47));//CYAN
        routes.add(createRoute(14, 41));//RED
        routes.add(createRoute(44, 48));//PINK
        routes.add(createRoute(29, 36));//ORANGE
        createCustomRoutes(routes); // BLACK & YELLOW
    }

    private ArrayList<Integer> createRoute(int start, int end){
        ArrayList<Integer> r = new ArrayList<>();
        if (FW.hasPath(end, start)) {
            for (DirectedEdge e : FW.path(end, start)) {
                r.add(e.to());
            }
        }
        r.add(end);
        return r;
    }

    private void createCustomRoutes(ArrayList<ArrayList<Integer>> routes){
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

    private void inputData(){
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("vertexCoordinates");
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] line = sCurrentLine.split(" ");
                coord[Integer.parseInt(line[0])][0] = Integer.parseInt(line[1]);
                coord[Integer.parseInt(line[0])][1] = Integer.parseInt(line[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void doubleEdge(int a, int b, int w){
        G.addEdge(new DirectedEdge(a, b, w));
        G.addEdge(new DirectedEdge(b, a, w));
    }

    AdjMatrixEdgeWeightedDigraph getG(){ return G; }
    int[][] getCoord(){ return coord; }
    ArrayList<ArrayList<Integer>> getRoutes(){ return routes; }


    private void setLearnedWeights(HashMap<String, Double> W, ArrayList<String> edgeTitles,
                                   String mes, String dia, String hora){

        for(String et : edgeTitles){
            String[] nodes = et.split(" ");
            int n1 = Integer.parseInt(nodes[0]);
            int n2 = Integer.parseInt(nodes[1]);
            String key = mes + " " + dia + " " + hora + " " + n1 + " " + n2;

            doubleEdge(n1, n2, W.get(key).intValue());
        }
    }



}
