import utils.GraphDrawable;

import javax.swing.*;

public class RoutingAgents {
    static final int NUM_OF_AGENTS = 1000;            // NA
    static final int NUM_OF_ITERATIONS = 10000;            // NI
    static final int MINUTES_WAITING_NEW_BUS = 4;        // ME
    static final int MINUTES_IN_ROUTE_WITHOUT_STOPS = 8; // MR

    static JFrame frame;
    static JFrame textFrame;
    static JTextArea textArea;
    static GraphData GD;
    static AgentModel agentModel;

    public static void main(String[] args) {
        GD = new GraphData();

        frame = new JFrame("Calculating routes...");
        textFrame = new JFrame("Routing Agents");

        textArea = new JTextArea();
        textFrame.add(textArea);
        textFrame.setSize(400, 1200);
        textFrame.setVisible(true);
        try {
            initModel();
        } catch (Exception e) {
            textArea.append(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void initModel() {
        agentModel = new AgentModel(MINUTES_WAITING_NEW_BUS, MINUTES_IN_ROUTE_WITHOUT_STOPS);
        agentModel.createAgentList(NUM_OF_AGENTS);

        long min = Long.MAX_VALUE;
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            frame.setTitle("Routes after iteration: " + i);
            GD.createRandomRoutes();
            long newTime = agentModel.calculateTravelTimes(GD);
            if (newTime < min) {
                min = newTime;
                System.out.println("Better route distribution - Iteration: " + i + " - Time: " + min);
                textArea.append("\n\nBetter route distribution - Iteration: " + i + " - Time: " + min);
                textArea.append("\n        Wait time average: " + agentModel.waitingTimeAvg);
                new GraphDrawable(GD.G, GD.coords, GD.routes).drawNewFrame(frame);
            }
        }
        textArea.append("\n\nAfter " + NUM_OF_ITERATIONS + " iterations, these are the best routes found.");
        System.out.println("After " + NUM_OF_ITERATIONS + " iterations, these are the best routes found.");

        textArea.append("\n");

        frame.setTitle("Routes after iteration: " + NUM_OF_ITERATIONS + " - COMPLETED");
    }
}
