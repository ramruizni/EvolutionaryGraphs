import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EvolutionMAIN {

    private static GraphData GD, SGD;

    private static AdjMatrixEdgeWeightedDigraph G, G2;
    private static int[][] coord;
    private static ArrayList<ArrayList<Integer>> routes, routes2;
    private static Random rand;

    private static HashMap<String, Integer> W;
    private static ArrayList<String> edgeTitles;

    public static void main (String [] args){

        rand = new Random();

        int cases = 10;
        W = DBcreator.createFakeWeights();
        DBlearner DBL = new DBlearner();
        DBL.resetW();
        DBL.learnFromDB(cases);

        //W = DBL.getLearnedW();
        edgeTitles = DBL.getEdgeTitles();

        GD = new GraphData(W, edgeTitles, "9", "5", "11");

        G = GD.getG();
        coord = GD.getCoord();
        routes = GD.getRoutes();

        drawDefaultMap();

        JFrame f0 = new JFrame("SITP - STARTING ROUTES");
        f0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f0.add(new DrawGraph(G, coord, routes, routes));
        f0.setSize(800, 600);
        f0.setVisible(true);

        JFrame f = new JFrame("SITP - TRAFFIC SIMULATION");
        f.setSize(800, 600);
        f.setLocation(800, 0);

        for(int i=0; i<300; i++) {

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String randMonth = Integer.toString(rand.nextInt(12)+1);
            String randDay = Integer.toString(rand.nextInt(7)+1);
            String randHour = Integer.toString(rand.nextInt(19)+4);

            runSimulation(f, randMonth, randDay, randHour);
            //runSimulation(f, "7", "7", "7");
            //runSimulation(f, "7", "7", "7");
            try {
                TimeUnit.MILLISECONDS.sleep(60); // 60
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        f0.removeAll(); f0.dispose();
        f.removeAll();  f.dispose();
    }


    private static void runSimulation(JFrame f, String month, String day, String hour){

        SGD = new GraphData(W, edgeTitles, month, day, hour);

        G2 = SGD.getG();
        routes2 = SGD.getRoutes();

        f.add(new DrawGraph(G2, coord, routes, routes2));
        f.setVisible(true);
    }

    private static void drawDefaultMap(){

    }



}
